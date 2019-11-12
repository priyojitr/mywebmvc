# Basic App Containerization
This is a basic app built using maven and deployed to tomcat server, all using **Docker** (_Dockerfile_).

Here are the steps that are followed:
1. git clone the project.
2. creating docker image with name:tag <br>
  `docker build --force-rm -t myapp:latest .`
3. this should build docker image of the app, along with pull dependent docker images <br>
  `docker images` - it should list all images in local repository.
    > * maven
    > * tomcat
4. this is an **optional** step - run the tomcat container - <br>
  `docker run -d -p 8080:8080 --name tomcat-dock tomcat:9.0.27-jdk8-openjdk`
    > this will host a tomcat container on port **`8080`**.
5. run the application container - <br>
  `docker run -d -p 80:8080 --name myapp-dock myapp:mvc`
    > * this will host the application on port `80`.
    > * this will create a container of the app with name as `myapp-dock` and deploy it to tomcat webapps folder.
6. to access the hosted app use the url - <br>
  `http://<hostname>/mywebmvcapp/`
    > war file name is - `mywebmvcapp`.
7. here are few basic useful commands to verify/debugging purpose - 
    > * to check list of running containers - `docker ps -a`
    > * to check logs of a container - `docker logs <container-name>` - container name can be obtained from above command.
    > * to SSH into a running container - `docker exec -it tomcat-dock /bin/bash` - it will provide a $prompt of the container. to exit, type `exit`.
8. to publish the image of the app to [docker hub](https://hub.docker.com) - a profile need to be created. <br>
    * login to docker hub profile - `docker login` - enter the credential as prompted.
    * create a tag for the image - `docker tag myapp:latest priyojitr/myapp:prhub`
        > **`priyojitr`** - is the namespace to which the image will be pushed. it will not pushed if namespace or profile name is not mentioned.
        > the image **will be overidden** if same name:tag is provided during _image_ `push` or `build`.
9. to re-use the published image, it needs to be pulled - <br>
  `docker pull priyojitr/myapp:prhub`
    > name of the image published in docker hub - **`priyojitr/myapp:prhub`**.
10. to update an existing a image due to changes in corresponding running container - <br>
  `docker commit -m "commit message" myapp2-dock myapp:v3`
    > this will update image with the changes made within the running container
    > **`myapp2-dock`** - name of the running container undergone changes.
    > **`myapp:v3`** - name of the updated image of the running container.
