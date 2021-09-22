# SmartCLIDE CI/CD
[![N|Solid](https://i2.wp.com/smartclide.eu/wp-content/uploads/2020/02/cropped-SmartClideRGBColor-1.png?w=120&ssl=1)](https://smartclide.eu/)

[![Actions Status](https://github.com/eclipse-researchlabs/smartclide-cicd/workflows/tests/badge.svg)](https://github.com/eclipse-researchlabs/smartclide-cicd/actions)
<br><br>

This is a SmartCLIDE CI/CD project. As of this moment the main purposes of this project is to be used for <b>demonstration</b>, provide some CI/CD services(Rest-Api) to other resources and handle some specific tasks/jobs.<br>

Also another goal of this project is to provide a way to be used as an early Proof of Concept regarding CI workflows demonstration and validations.

# Overview
Below are some basic steps and information of what this service can provide and how to use it.
<br><br>
The goal of this service is to provide the ability to containerize and include CI/CD support to services/projects generated from the user by the SmartCLIDE service creation process and SmartCLIDE-jbpm or any other supported types of projects.<br>
This happens by generating CI pipelines, docker files and any other necessary file is needed to containerize a project and include pipeline flow support.<br>

Below are the basic steps to make a non-containerized project containerized, and also included it in a CI/CD flow.
## CI/CD support
To generate a CI .gitlab-ci.yml for a project, consume the following API.<br>
GET `` cicd/api/templates/pipelines/{type}/download `` <br>

Request parameter <b>type</b> is the type of the project.<br>
For example for a maven project.<br>
GET `` cicd/api/templates/pipelines/maven/download `` <br>
will produce a .gitlab-ci.yml specific for a maven project containing maven commands for building and testing.<br>

Other available query parameters.
- appName
- variables
  - example: `` cicd/api/templates/pipelines/maven/download?releaseOnly=master&variables=GIT_DEPTH: 10,PARAM: value ``
  will include in .gitlab-ci.yml
  ```
    variables:
      GIT_DEPTH: 10
      PARAM: value
  ```
- image
  - replace the default docker image with the given one
- testWhen
- testOnly
  - default branch is master
- reportOnly
  - default branch is master
- reportWhen
- releaseOnly
  - default branch is master
- releaseWhen	
- buildCommand
- testCommand

## Docker support
To generate a Dockerfile for a project, consume the following API.<br>
GET `` cicd/api/templates/dockerfiles/{type}/download?extension={extension}``

Request parameter <b>type</b> is the type of the project and <b>extension</b> is the deplyment extension<br>
For example for a maven project.<br>
GET `` cicd/api/templates/dockerfiles/maven/download?extension=war`` <br>
will produce a Dockerfile image with instructions specific for a maven and java project.<br>

To include extra Build commands<br>
GET `` cicd/api/templates/dockerfiles/maven/download?extension=war&extraCommands=RUN mv target/externallib.jar ./externallib.jar `` <br>

Also to generate a .dockerignore for this project consume the following API.<br>
GET `` cicd/api/templates/dockerfiles/dockerignore/download``

## Putting it all together
Place the above files (.gitlab-ci.yml, Dockerfile, .dockerignore) in the root of the project and commit.<br>
In gitlab the following events will take place.<br>
- CI pipeline flow will start.
- Steps specified in the pipeline flow(build, test...) will be executed.
- An docker image will be uploaded to the gitlab's repo container registry.


## Build and run this project locally
To build and run this project locally in a docker environment.<br>
Build
```shell
docker build -t smartclide-cicd .
```
Run
```shell
docker run --rm -p 8080:8080 smartclide-cicd
```




