# springboot-items-api-istio Helm Chart

This helm chart was written to help get the organization started with using helm charts. What it demonstrates is only the beginning of what is possible with helm. Other more advanced use cases can leverage the concept of parent/child charts to help establish a standard.

## How to Use

From the root of the project, run one of these commands:

- initial installation

```
helm install springboot-items-api -f manifests/helm/springboot-items-api-istio/values-dev.yaml -n dev manifests/helm/springboot-items-api-istio/
```

- upgrade (new revision)

```
helm upgrade springboot-items-api -f manifests/helm/springboot-items-api-istio/values-dev.yaml -n dev manifests/helm/springboot-items-api-istio/
```

If you have modified the chart or values file, it is advisable to validate it first with the "--dry-run" flag, which will provide the processed yaml as output for your inspection, or an error message.

NOTE: While it is possible to run a "helm upgrade --install" to satisfy both cases it is not recommended. Doing so removes a saftey feature for ensuring you are in the correct namespace. Without the "--install" flag an upgrade will fail should it not find the deployment name already in the namespace, preventing the deployment.
