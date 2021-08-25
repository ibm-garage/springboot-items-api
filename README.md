# Spring Boot Items-API Archetype Microservice

This project is intended to serve as a CRUD archetype using Spring Boot and Spring Data in an imperative (non-reactive) way.  It is also intended to demonstrate the use of Aspect Oriented Programming (AOP) for trace logging, database exception conversion, and annotation based event processing.

## Building the code and packging the JAR into a container
This code is meant to be containerized. If you are deploying to Kubernetes you will need to build and containerize the application first.  

NOTE: If deploying to OpenShift, how you build may be different depending on your needs and if you use the manifests/openshift/build.yaml as described in the "Deploying to Openshift" section.

NOTE: If you don't want to or can't build it yourself, you can use the container that is deployed in dockerhub at "michaelsteven/springboot-items-api:0.0.1"

PREREQUISITES:
- Java 1.8
- maven
- docker (or podman)

Steps:
1. clone down this git repository
2. build the code using maven (mvn clean install)
3. Use the Dockerfile to create the container.  Example:
```
docker build -t <<your-registry-namespace>>/springboot-items-api:0.0.1 .
```
4. Push the container to your docker registry
```
docker login
docker push <<your-registry-namespace>>/springboot-items-api:0.0.1
```
NOTE: Be sure to use your own namespace name instead of "<<your-registry-namespace>"

## Deploying to Kubernetes
These instructions will walk you through using the helm chart found in the manifiests folder of this repository to deploy the application from the command line.

PREREQUISITES:
- kubectl cli
- helm cli

How to deploy:
1. clone down the code
2. build and publish the docker container to your container registry using the steps in the "Building the code and packging the JAR into a container" 
Alternatively, you could use my already published container.
3. edit the 'manifests/helm/springboot-items-api/values.yaml with your values
4. log into your kubernetes cluster from the CLI (this may vary depending on where your kubernetes cluster is deployed)
5. verify you are connected to the cluster you expect
```
kubectl config current-context
```
6. If needed, create your namespace
```
kubectl create namespace dev
```
7. deploy the application.yaml into a ConfigMap in your namespace
```
kubectl create configmap springboot-items-api --from-file=application.yaml -n dev
```
8. create a secret with your database credentials:
```
oc create secret generic mysql-credentials --from-literal=username=root --from-literal=password=password
```
9. Add any other secrets you are using, including the ingress TLS secret.  Steps for this may vary.  Alternatively you could comment out the "tls" section in "manifests/helm/springboot-items-api/templates/ingress.yaml" 
10. Dry run the helm chart to look for problems
```
helm create -f manifests/helm/springboot-items-api/Chart.yaml springboot-items-api -n dev --dry-run ./manifests/helm/springboot-items-api
```
11. Once happy with the output, run it for real without the --dry-run option
```
helm create -f manifests/helm/springboot-items-api/Chart.yaml springboot-items-api -n dev --dry-run ./manifests/helm/springboot-items-api
```

## Deploying to Openshift
Explore running in OpenShift with a Developer Sandbox (free) account available in RedHat: https://developers.redhat.com/developer-sandbox.

Below is an step-by-step of a way to build and deploy this microservice using the CLI. They could also be used as part of an external CI/CD pipeline.

1. clone down this git repository
2. build the code with maven (mvn clean install)
3. install the OC CLI
4. log in to OpenShift with the OC CLI using a token generated from the OpenShift Console
5. change to the right project (if needed)
6. create a ConfigMap with your application settings:
```
oc create configmap springboot-items-api --from-file=application.yaml
```
7. create a secret with your database credentials:
```
oc create secret generic mysql-credentials --from-literal=username=root --from-literal=password=password
```
8. create a build configuration for the application using a template:
```
oc new-app --file=./manifests/openshift/build.yaml --param=APP=springboot-items-api
```
9. initiate a build of the build configuration, uploading the current directory for use in the Dockerfile:
```
oc start-build springboot-items-api --from-dir . 
```
This will build the Dockerfile and publish it into openshift's integrated container registry.
10. Use the deployment template to deploy the application:
```
oc process -f manifests/openshift/deployment.yaml --param=REGISTRY=image-registry.openshift-image-registry.svc:5000/michael-hepfer-dev | oc apply -f -
```

## Licensing
Licensed under the Apache License, Version 2.0 (the "License"); you may not use these files except in compliance with the License.  You may obtain a copy of the License at http://ww.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the License for the specific language governing permission and limitations under the License.