

self.addEventListener('push', function(event) {
    console.log('[Service Worker] Push had this data.text:' + event.data.text());

    var response = JSON.parse(event.data.text());

    var title = response.title;
    var options = {
        body: response.body,
        data: response.data,
        icon: response.iconUrl,
        image: response.imageUrl,
        sound: response.soundUrl,
        actions: response.actions,
        renotify: response.renotify,
        requireInteraction: response.requireInteraction,
        tag: response.tag,

        //mobile only
        badge: response.badgeUrl,
        vibrate: response.vibrate
    };

    event.waitUntil(self.registration.showNotification(title, options));
});


self.addEventListener('notificationclick', function(event) {
    console.log('[Service Worker] Notification click Received.');
    console.log('event.notification.data:' + event.notification.data);
    console.log('event.notification.data.offerUrl:' + event.notification.data.offerUrl);

    var data = event.notification.data;

    event.notification.close();

    if (!event.action) {
        event.waitUntil(clients.openWindow(data.offerUrl));
    } else {
        var action = data.actionsUrls.find(function(node) {
            return node.action === event.action;
        });
        console.log('action.url:' + action.url);
        if(action.url != null)
            event.waitUntil(clients.openWindow(action.url));
        else
            event.waitUntil(clients.openWindow(data.offerUrl));

    }

    //event.waitUntil(clients.openWindow(data.offerUrl));
});