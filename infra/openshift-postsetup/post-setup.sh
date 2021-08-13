#!/usr/bin/env bash:

# make image registry publicly available
oc patch configs.imageregistry.operator.openshift.io/cluster --patch '{"spec":{"defaultRoute":true}}' --type=merge
oc apply -f 00-pipelines-subscription.yml
oc new-project quarkus-devmode || oc project quarkus-devmode
oc apply -f 01-image-streams.yml