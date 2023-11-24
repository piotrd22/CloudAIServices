#!/usr/bin/env bash

# Contenerized Terraform
# TERRAFORM=docker run --rm -it -v `pwd`:/workdir terraform

# Plain CLI Terraform
TERRAFORM=terraform

$TERRAFORM $@
