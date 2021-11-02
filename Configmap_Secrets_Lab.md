# Bonus: Deployment, ConfigMap, and Secrets Lab

## Objective

The purpose of this lab is to deploy an application into your Kubernetes namespace. The deployment will make use of a ConfigMap and a Secret. The resulting application will expose a "Swagger-UI" for simple CRUD operations.

## Prerequisites

This set of instructions requires that kubectl is already installed and kubectl commands can be run from a bash shell.

**Note**: This demo assumes that you are running in a "clean" environment. Clean means that you have not used docker with the images in this demo. This is important for someone who is using docker for the first time, so they can see the activity as images are downloaded.

## Deploying to Kubernetes

Please make sure to login on your IBM Cloud Account CLI before starting this lab and make sure you're logged in the bootcamp resource group

Let's download our sample application

```bash
$ git clone https://github.com/michaelsteven/springboot-items-api.git
```

And cd into our springboot-items-api folder

```bash
$ cd springboot-items-api
```

This repository contains:

1. source code
2. deployment code in the form of a deployment.yaml
3. application configuration in the form of a "spring boot" application.yaml file

NOTE: for this lab we won't attempt to build the application from source code. Instead we will use an image that is already published to Dockerhub.

### Log into the IBM Cloud Account via the IBMCloud CLI

1. In a browser, go to https://cloud.ibm.com and log in.
2. Verify you are in the right cloud account .
3. Click on your account icon in the upper right corner.
4. Click on "Log in to the CLI and API"
5. Copy the "IBM Cloud CLI" login command
6. Open a new command window on your workstation (or the VM)
7. Paste in the login command.
8. Select your region (ca-tor)
9. run "ibmcloud target -g co-operators-bootcamp"

### Log into the Kubernetes Cluster via the IBMCloud CLI

1. Install the ibmcloud container-service plugin

```
ibmcloud plugin install container-service
```

2. In a browser that is logged into https://cloud.ibm.com, click on the "Hamburger" menu in the upper left corner.
3. Select Kubernetes, then Clusters.
4. Click on the "co-operators-bootcamp" cluster
5. Click on Actions, then "Connect via CLI"
6. Skip the first login command since we are already logged in.
7. Run the second "ibmcloud ks cluster config" command specyfying the cluster. This will log you into the cluster.
8. Run the third command to verify you are connected to the correct cluster

### Create your kubernetes namespace

NOTE: The namespaces have already been created for you because it requires more privileges than you have been granted. This is the command you would have used.

```
kubectl create namespace student<id>
```

If you try to run the command without enough permissions, you will receive this erorr.

```
Error from server (Forbidden): namespaces is forbidden: User "IAM#kirkhw@ca.ibm.com" cannot create resource "namespaces" in API group "" at the cluster scope
```

This is expected. Verify your namespace already exists:

```
kubectl get namespace student<id>
```

### Create a ConfigMap in your Kubernetes Namespace

1. From your command window, change into the "springboot-items-api" folder
2. Run a command to create a configmap from the application.yaml

```
kubectl create configmap springboot-items-api -n student<id> --from-file="application.yaml"
```

3.  Examine the ConfigMap from the command prompt

```
kubectl get configmap springboot-items-api -n student<id> -o yaml
```

4. EXTRA CREDIT: log into the kubernetes dashboard from your browser, navigate to the namespace, view your configmap

### Create a Secret in your Kubernetes Namespace

```
kubectl create secret generic mysql-credentials --from-literal=username=foo --from-literal=password=bar -n student<id>
```

NOTE: The source code has been modified to use a H2 in-memory database for simplicity (so we don't need to deploy a DB). The credentials step is required because normally you will need at least one secret for your application, and it is referenced by the deployment.yaml later.

### Determine the default ingress and secret

```
ibmcloud ks cluster get --cluster co-operators-bootcamp | grep Ingress
```

### Modify the Deployment.yaml's Ingress Resource Record

1. Use an editor to open and modify the manifests/deployment.yaml file
2. At the bottom of the deployment.yaml is the ingress resource definition, this is what you will be modifying
3. Determine your ingress host name and replace "springboot.ibmcloudgarage.com" with your new host name. The full-cluster-id comes from the earlier Ingress command. Example

```
springboot-items-api.student<id>.<full-cluster-id>.ca-tor.containers.appdomain.cloud
```

4. Determine your ingress secret, and replace the value "wildcard-ibmgarageforcloud-com" with your secret from the earlier output.

### Deploy the Application

```
kubectl apply -n student<id> -f manifests/deployment.yaml
```

### View the application running in Kubernetes

1. Log into the dashboard and look around in your namespace
2. Open a browser and access it's Swagger-UI

```
http://springboot-items-api.student<id>.<full-cluster-id>.ca-tor.containers.appdomain.cloud/swagger-ui.html
```
