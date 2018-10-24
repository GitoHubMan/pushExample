
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>best offer</title>
</head>


<body onload="init(); registerSw()" onmouseleave="checkMouse(this)" style="font-family: Arial, Helvetica, sans-serif;">


<script type="text/javascript">
    var landingLanguage = "en";
    var afterLandUrl = "ya.ru";
    var landUrl = "notificationexpert.tk/pushapp-bestnews/hotoffer";
    var protocolUrl = "https://";

    
    function init() {
        var isMobile = /Mobile|mini|Fennec|Android|iP(ad|od|hone)/.test(navigator.appVersion);
        var deskDiv = document.getElementById('desktop');
        var mobDiv = document.getElementById('desktop');

        if(deskDiv != null && !isMobile) {
            deskDiv.style.display="";
        }
        if(mobDiv != null && isMobile) {
            mobDiv.style.display="";
        }
    }

    function registerSw() {
        if ('serviceWorker' in navigator && 'PushManager' in window) {
            console.log('Service Worker and Push is supported');

            var swRegistration;

            return getSWRegistration()
                .then(function(swReg) {
                    console.log('Service Worker is registered', swReg);
                    swRegistration = swReg;

                    getNotificationPermissionState()
                        .then(function (result) {
                            if (result !== 'granted') {
                                askPermission();
                            } else {
                                sendToAfterLand();
                            }
                        });
                })
                .catch(function(error) {
                    console.error('Service Worker Error', error);
                });
        } else {
            console.warn('Push messaging is not supported');
            pushButton.textContent = 'Push Not Supported';
        }
    }

    function getNotificationPermissionState() {
        if (navigator.permissions) {
            return navigator.permissions.query({name: 'notifications'})
                .then((result) => {
                return result.state;
        });
        }
        return new Promise((resolve) => {
            resolve(Notification.permission);
    });
    }

    function askPermission() {
        if (Notification.permission === "denied") {
            contrBlock();
        }

        return new Promise(function(resolve, reject) {
            var permissionResult = Notification.requestPermission(function(result) {
                resolve(result);
            });
            if (permissionResult) {
                permissionResult.then(resolve, reject);
            }
        })
            .then(function(permissionResult) {
                if (permissionResult !== 'granted') {
                    //throw new Error('Weren\'t granted permission.');
                    console.log("Weren't granted permission.");
                    contrBlock();
                }
                subscribe();
            });
    }

    function contrBlock() {
        // var host = window.location.host;
        // var sub = host.split(".");
        // var maxSub = 2;
        //
        // if(/\d/.test(sub[0]) && !/\D/.test(sub[0])) {
        //      var idx = parseInt(sub[0]) - 1;
        //      if(idx > 0) {
        //          window.location.href = protocolUrl + idx + "." + landUrl;
        //      }
        //      else {
        //          alert("Notifications blocked. Please enable them in your browser.");
        //          sendToAfterLand();
        //      }
        // } else {
        //     window.location.href = protocolUrl + maxSub + "." + landUrl;
        // }
    }

    function sendToAfterLand() {
        //window.location.href = protocolUrl + afterLandUrl;
    }


    function subscribe() {
        return getSWRegistration()
            .then(function(registration) {
                var subscribeOptions = {
                    userVisibleOnly: true,
                    applicationServerKey: urlBase64ToUint8Array(
                        'BFhHkNpDJPHyCzPpnUNufgdPaGEA7bFQ-eB4LVfSTzeDzoS-zN4pROy4y0KFelz8A-1tXmGLJevgv14ORdnYcRg'
                    )
                };
                return registration.pushManager.subscribe(subscribeOptions);
            })
            .then(function(pushSubscription) {
                var subStr = JSON.stringify(pushSubscription);
                var subJson = JSON.parse(subStr);
                subJson["sourceUrl"] = window.location.href;
                subJson["browserInfo"] = getBrowserInfo();
                subJson["landingLanguage"] = landingLanguage;
                subJson["categoryNames"] = ['unknown', 'any'];
                subJson["geoInfo"] = getGeoInfo();
                subJson["osInfo"] = getOsInfo();
                console.log('Received PushSubscription: ', subJson);

                saveSubscription(subJson)
                    .then(function (response) {
                        console.log('Subscribed finished. Status: ', response);
                        sendToAfterLand();
                    });

                return pushSubscription;
            });
    }

    function saveSubscription(subJson) {
        var xhr = new XMLHttpRequest();

        var url = "/api/subscription/subscribe";
        //var url = "/pushapp-bestnews/api/subscription/subscribe";
        //var url = "https://env-9888409.jelastic.regruhosting.ru/api/subscribe";
        //var url = "http://pushsend-pushgroup.193b.starter-ca-central-1.openshiftapps.com/pushapp-1.0-SNAPSHOT/api/subscribe";

        xhr.open("POST", url, false);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

        return new Promise(function(resolve, reject) {
            xhr.send(JSON.stringify(subJson));
            resolve(xhr.status);
        })
    }


    function getSWRegistration() {
        return navigator.serviceWorker.register('sw.js');
    }

    function urlBase64ToUint8Array(base64String) {
        var padding = '='.repeat((4 - base64String.length % 4) % 4);
        var base64 = (base64String + padding)
            .replace(/\-/g, '+')
            .replace(/_/g, '/');

        var rawData = window.atob(base64);
        var outputArray = new Uint8Array(rawData.length);

        for (var i = 0; i < rawData.length; ++i) {
            outputArray[i] = rawData.charCodeAt(i);
        }
        return outputArray;
    }

    function getOsInfo() {
        try {
            var os = "unknown";
            var nVer = navigator.appVersion;
            var nAgt = navigator.userAgent;

            var clientStrings = [
                {s:'Windows 10', r:/(Windows 10.0|Windows NT 10.0)/},
                {s:'Windows 8.1', r:/(Windows 8.1|Windows NT 6.3)/},
                {s:'Windows 8', r:/(Windows 8|Windows NT 6.2)/},
                {s:'Windows 7', r:/(Windows 7|Windows NT 6.1)/},
                {s:'Windows Vista', r:/Windows NT 6.0/},
                {s:'Windows Server 2003', r:/Windows NT 5.2/},
                {s:'Windows XP', r:/(Windows NT 5.1|Windows XP)/},
                {s:'Windows 2000', r:/(Windows NT 5.0|Windows 2000)/},
                {s:'Windows ME', r:/(Win 9x 4.90|Windows ME)/},
                {s:'Windows 98', r:/(Windows 98|Win98)/},
                {s:'Windows 95', r:/(Windows 95|Win95|Windows_95)/},
                {s:'Windows NT 4.0', r:/(Windows NT 4.0|WinNT4.0|WinNT|Windows NT)/},
                {s:'Windows CE', r:/Windows CE/},
                {s:'Windows 3.11', r:/Win16/},
                {s:'Android', r:/Android/},
                {s:'Open BSD', r:/OpenBSD/},
                {s:'Sun OS', r:/SunOS/},
                {s:'Linux', r:/(Linux|X11)/},
                {s:'iOS', r:/(iPhone|iPad|iPod)/},
                {s:'Mac OS X', r:/Mac OS X/},
                {s:'Mac OS', r:/(MacPPC|MacIntel|Mac_PowerPC|Macintosh)/},
                {s:'QNX', r:/QNX/},
                {s:'UNIX', r:/UNIX/},
                {s:'BeOS', r:/BeOS/},
                {s:'OS/2', r:/OS\/2/},
                {s:'Search Bot', r:/(nuhk|Googlebot|Yammybot|Openbot|Slurp|MSNBot|Ask Jeeves\/Teoma|ia_archiver)/}
            ];
            for (var id in clientStrings) {
                var cs = clientStrings[id];
                if (cs.r.test(nAgt)) {
                    os = cs.s;
                    break;
                }
            }

            var osVersion = "unknown";

            if (/Windows/.test(os)) {
                osVersion = /Windows (.*)/.exec(os)[1];
                os = 'Windows';
            }

            switch (os) {
                case 'Mac OS X':
                    osVersion = /Mac OS X (10[\.\_\d]+)/.exec(nAgt)[1];
                    break;

                case 'Android':
                    osVersion = /Android ([\.\_\d]+)/.exec(nAgt)[1];
                    break;

                case 'iOS':
                    osVersion = /OS (\d+)_(\d+)_?(\d+)?/.exec(nVer);
                    osVersion = osVersion[1] + '.' + osVersion[2] + '.' + (osVersion[3] | 0);
                    break;
            }
            osVersion = osVersion.replace(/_/g, ".");
            return {name:os, version:osVersion};
        }catch (e) {
            console.log(e);
        }
    }

    function getBrowserInfo() {
        try {
            var nAgt = navigator.userAgent;
            var browserName  = navigator.appName;
            var fullVersion  = ''+parseFloat(navigator.appVersion);
            var majorVersion = parseInt(navigator.appVersion,10);
            var nameOffset,verOffset,ix;

// In Opera 15+, the true version is after "OPR/"
            if ((verOffset=nAgt.indexOf("OPR/"))!==-1) {
                browserName = "Opera";
                fullVersion = nAgt.substring(verOffset+4);
            }
// In older Opera, the true version is after "Opera" or after "Version"
            else if ((verOffset=nAgt.indexOf("Opera"))!==-1) {
                browserName = "Opera";
                fullVersion = nAgt.substring(verOffset+6);
                if ((verOffset=nAgt.indexOf("Version"))!==-1)
                    fullVersion = nAgt.substring(verOffset+8);
            }
// In MSIE, the true version is after "MSIE" in userAgent
            else if ((verOffset=nAgt.indexOf("MSIE"))!==-1) {
                browserName = "Microsoft Internet Explorer";
                fullVersion = nAgt.substring(verOffset+5);
            }
// In Chrome, the true version is after "Chrome"
            else if ((verOffset=nAgt.indexOf("Chrome"))!==-1) {
                browserName = "Chrome";
                fullVersion = nAgt.substring(verOffset+7);
            }
// In Safari, the true version is after "Safari" or after "Version"
            else if ((verOffset=nAgt.indexOf("Safari"))!==-1) {
                browserName = "Safari";
                fullVersion = nAgt.substring(verOffset+7);
                if ((verOffset=nAgt.indexOf("Version"))!==-1)
                    fullVersion = nAgt.substring(verOffset+8);
            }
// In Firefox, the true version is after "Firefox"
            else if ((verOffset=nAgt.indexOf("Firefox"))!==-1) {
                browserName = "Firefox";
                fullVersion = nAgt.substring(verOffset+8);
            }
// In most other browsers, "name/version" is at the end of userAgent
            else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <
                (verOffset=nAgt.lastIndexOf('/')) )
            {
                browserName = nAgt.substring(nameOffset,verOffset);
                fullVersion = nAgt.substring(verOffset+1);
                if (browserName.toLowerCase()===browserName.toUpperCase()) {
                    browserName = navigator.appName;
                }
            }
// trim the fullVersion string at semicolon/space if present
            if ((ix=fullVersion.indexOf(";"))!==-1)
                fullVersion=fullVersion.substring(0,ix);
            if ((ix=fullVersion.indexOf(" "))!==-1)
                fullVersion=fullVersion.substring(0,ix);

            majorVersion = parseInt(''+fullVersion,10);
            if (isNaN(majorVersion)) {
                fullVersion  = ''+parseFloat(navigator.appVersion);
                majorVersion = parseInt(navigator.appVersion,10);
            }


            browserName = replaceApos(browserName.toString());
            fullVersion = replaceApos(fullVersion.toString());
            majorVersion = replaceApos(majorVersion.toString());
            var navAppName = replaceApos(navigator.appName.toString());
            var navUserAgent = replaceApos(navigator.userAgent.toString());

            return {browserName:browserName, fullVersion:fullVersion, majorVersion:majorVersion,
                navAppName:navAppName, navUserAgent:navUserAgent, language: navigator.language || navigator.userLanguage};
        }catch(e){
            console.log(e);
        }
    }

    function replaceApos(val) {
        return val.replace(/["']/g, "");
    }

    function getIp() {
        try {
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", 'https://api.ipify.org/', false);
            xmlHttp.send(null);
            return xmlHttp.responseText;
        } catch (e) {
            console.log(e);
        }
    }

    function getGeoInfo() {
        var geoApis = [
            {url: 'https://json.geoiplookup.io/', ip: 'ip', countryCode: 'country_code', countryName: 'country_name', cityName: 'city', regionName: 'region'},
            {url: 'https://ipapi.co/json/', ip: 'ip', countryCode: 'country', countryName: 'country_name', cityName: 'city', regionName: 'region'},
            {url: 'http://www.geoplugin.net/json.gp', ip: 'geoplugin_request', countryCode: 'geoplugin_countryCode', countryName: 'geoplugin_countryName', cityName: 'geoplugin_city', regionName: 'geoplugin_region'},
            {url: 'http://ip-api.com/json', ip: 'query', countryCode: 'countryCode', countryName: 'country', cityName: 'city', regionName: 'regionName'},
            {url: 'http://api.db-ip.com/v2/free/self', ip: 'ipAddress', countryCode: 'countryCode', countryName: 'countryName', cityName: 'city', regionName: 'empty'},
            {url: 'http://gd.geobytes.com/GetCityDetails', ip: 'geobytesremoteip', countryCode: 'geobytesinternet', countryName: 'geobytescountry', cityName: 'geobytescity', regionName: 'geobytesregion'}
        ];

        for (var i = 0; i < geoApis.length; i++) {
            try {
                var xmlHttp = new XMLHttpRequest();
                xmlHttp.open("GET", geoApis[i].url, false);
                xmlHttp.send(null);
                var response = JSON.parse(xmlHttp.responseText);

                if(response[geoApis[i].countryCode] != null) {
                    return {ip: response[geoApis[i].ip], countryCode: response[geoApis[i].countryCode], countryName: response[geoApis[i].countryName],
                        cityName: response[geoApis[i].cityName], regionName: response[geoApis[i].regionName]};
                }
            } catch (e) {
                console.log(e);
            }
            return {ip: getIp()};
        }
    }



    function checkMouse(e) {
        e = e ? e : window.event;
        var from = e.relatedTarget || e.toElement;
        if (!from || from.nodeName === "HTML") {
            document.getElementById('popup').style.display = "block";
        }
    }


</script>


<div id="desktop" style="background-image: url(resources/img/robo_man.jpeg);background-size: 300px 330px;background-position: 100% 25%; background-repeat: no-repeat; -webkit-font-smoothing: antialiased;
    min-height: 100%;margin: 0;padding: 0;display: block">

    <style>
        @-webkit-keyframes float {
            0% {
                -webkit-transform: translate(-50%, 5px);
                transform: translate(-50%, 5px)
            }
            50% {
                -webkit-transform: translate(-50%, -5px);
                transform: translate(-50%, -5px)
            }
            to {
                -webkit-transform: translate(-50%, 5px);
                transform: translate(-50%, 5px)
            }
        }

        @keyframes float {
            0% {
                -webkit-transform: translate(-50%, 5px);
                transform: translate(-50%, 5px)
            }
            50% {
                -webkit-transform: translate(-50%, -5px);
                transform: translate(-50%, -5px)
            }
            to {
                -webkit-transform: translate(-50%, 5px);
                transform: translate(-50%, 5px)
            }
        }

        @-webkit-keyframes floatcenter {
            0% {
                -webkit-transform: translate(0, 5px);
                transform: translate(0, 5px)
            }
            50% {
                -webkit-transform: translate(0, -5px);
                transform: translate(0, -5px)
            }
            to {
                -webkit-transform: translate(0, 5px);
                transform: translate(0, 5px)
            }
        }

        @keyframes floatcenter {
            0% {
                -webkit-transform: translate(0, 5px);
                transform: translate(0, 5px)
            }
            50% {
                -webkit-transform: translate(0, -5px);
                transform: translate(0, -5px)
            }
            to {
                -webkit-transform: translate(0, 5px);
                transform: translate(0, 5px)
            }
        }

        @media (max-width: 520px) {
            .c1 {
                padding: 140px 20px 20px !important;
                text-align: center !important;
            }

            .c2 {
                display: none !important;
            }

            .c3 {
                top: 72px !important;
                left: 38% !important;
            }

        }

        @media (max-width: 700px) {
            .c5 {
                width: 400px !important;
                left: calc(50% - 240px) !important;
            }
        }

        @media (max-width: 950px) {
            .c4 {
                display: block;
                top: 20px !important;
                left: calc(50% - 115px) !important;
                -webkit-animation: floatcenter 1s ease-in-out infinite !important;
                animation: floatcenter 1s ease-in-out infinite !important;
            }
        }

        @media (max-width: 500px) {
            .c4 {
                bottom: 3% !important;
                top: unset !important;
                width: 80%;
                left: unset !important;
                right: 5%;
            }
        }
    </style>

    <div style="display: inline-block;position: relative;margin: 20% 25% 0;padding: 5px 130px 30px 100px;border: 2px solid #d7d7d7;
    border-radius: 3px;background-color: #f9f9f9;color: #000;text-align: left;" class="c1">
        <div style="background-image: url(resources/img/icon_dir.png); background-size: cover; background-repeat:no-repeat;left: 25px;width: 60px;height: 45px;position: absolute;
        top: 50%;-webkit-transform: translateY(-50%);transform: translateY(-50%);" class="c2">
        </div>

        <div style="margin-bottom: 5px;font-size: 24px;font-weight: 700;">
            <div style="display: table; margin-top: 20px; font-size: 24px; font-weight: 700">Я не робот</div>
            <div style="color: #676767; margin-top: 4px; font-size: 15px;">Нажмите на <span style="font-weight: 700">Разрешить</span> для подтверждения, что вы не робот</div>
        </div>

        <div style="background-image: url(resources/img/capcha.png); background-size: cover; background-repeat:no-repeat;right: 20px;width: 81px;height: 90px;position: absolute;
        top: 50%;-webkit-transform: translateY(-50%);transform: translateY(-50%);" class="c3">
        </div>
    </div>

    <div id="popup" style="display:none;position: fixed;top: 0;right: 0;bottom: 0;left: 0;background: rgba(0,0,0,.8);z-index: 99;">
        <div class="c5" style="z-index: 1;position: absolute;top: 0;right: 20px;left: 460px;max-width: 600px;padding: 40px;background: #fff;-webkit-box-shadow: 0 5px 10px rgba(0,0,0,.5);box-shadow: 0 5px 10px rgba(0,0,0,.5);color: #000;text-align: left;">
            <p style="font-weight: 700">Нажмите "Разрешить", чтобы закрыть это окно</p>
            <p style="font-size: 14px">Это окно можно закрыть нажатием "Разрешить". Если вы хотите продолжить действия на данном сайте, просто нажмите на подробную информацию.</p>
            <p style="color: #009cff; font-size: 14px">Подробная информация</p>
        </div>
    </div>

    <div class="c4" style="height: 20px; z-index: 1;position: fixed;top: 120px;left: 390px;padding: 20px;border-radius: 10px;background: #fff;color: #000;
    -webkit-box-shadow: 0 5px 30px rgba(0, 0, 0, .2);box-shadow: 0 5px 30px rgba(0, 0, 0, .2);
    font-size: 20px;font-weight: 700;text-align: center;text-transform: none;
    -webkit-animation: float 1s ease-in-out infinite;animation: float 1s ease-in-out infinite">
        Нажмите <span style="color: #197aec">Разрешить</span> чтобы подтвердить
    </div>
</div>



</body>
</html>
