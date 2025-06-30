# Simple Currency convertor application
this is a aplicantion that using Java Swing to build it
It is simple that covert the currency we chose in menu then using API key to get rates from [ExchageRates](https://exchangeratesapi.io/)
# Important
Because this app using API from [ExchageRates](https://exchangeratesapi.io/), so please get key API from that first then you will able to use it. thank you
# How to run 
Fist clone the repo and build the **Dockerfile**
```
git clone https://github.com/binhnt3002/Pet-project.git
cd Pet-project/
docker build -t exchange-app .
```
On **Linux** 
```
xhost +local:docker
docker run -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix exchange-app
```
On **Windows** recommend to use the [Wsl2](https://learn.microsoft.com/en-us/windows/wsl/install) from Microsoft
```
docker run -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix exchange-app
```
if not using **Wsl2**, then first install [VcXsrv](https://sourceforge.net/projects/vcxsrv/) to start the X server
After that run **Xlaunch** and setting like :
 - Multiple windows
 - Display number: 0
 - Start no client
 - Disable access control

Next find your host IP address by running ipconfig in the command prompt and noting your IPv4 address. Then, use the `DISPLAY` variable to point to your VcXsrv server, replacing `YOUR_HOST_IP` with your IP address.
```
docker run -e DISPLAY=host.docker.internal:0.0 exchange-app
```
