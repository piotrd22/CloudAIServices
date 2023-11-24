terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.26.0"
    }
  }
}

provider "aws" {
  # Configuration options
  region = "eu-west-1"
}

locals {
  # This prefix is added to all resource names
  # to make sure resources used by different students don't interfere.
  student_prefix = "pdamrych"
}