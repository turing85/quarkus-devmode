#!/usr/bin/env bash
set -e

copy_image() {
  skopeo copy docker://"$1"@"$2" docker://"$3":"$4"
}

# install pipeline tasks
oc apply -f https://raw.githubusercontent.com/tektoncd/catalog/main/task/git-clone/0.4/git-clone.yaml
oc apply -f https://raw.githubusercontent.com/tektoncd/catalog/main/task/maven/0.2/maven.yaml
oc apply -f https://raw.githubusercontent.com/tektoncd/catalog/main/task/kaniko/0.4/kaniko.yaml

# create image streams for pipeline task runner images
oc apply -f 00-image-streams.yml

# allow pipeline sa to pull images
oc policy -n quarkus-devmode add-role-to-user \
    system:image-puller \
    system:serviceaccount:quarkus-devmode:pipeline

# install tekton pipeline in project
oc apply -n quarkus-devmode -f 01-pvc.yml
oc apply -n quarkus-devmode -f 02-github-secret.yml
oc apply -n quarkus-devmode -f 03-pipeline.yml
oc apply -n quarkus-devmode -f 04-trigger.yml