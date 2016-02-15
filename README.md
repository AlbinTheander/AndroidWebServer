# AndroidWebServer

This is a very simple demo app to show how to use NanoHttpdServer in Android.

To keep it simple, there are many things that aren't considered. Especially the life cycle of the main
activity should absolutely be handled. Right now, rotating the device will most likely crash the
app if the web server is running.

