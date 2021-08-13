#!/usr/bin/env bash:

# make image registry publicly available
oc patch configs.imageregistry.operator.openshift.io/cluster --patch '{"spec":{"defaultRoute":true}}' --type=merge

# install pipeline operator
oc apply -f 00-pipelines-subscription.yml

# create project and image stream
oc new-project quarkus-devmode || oc project quarkus-devmode
oc apply -f 01-image-streams.yml