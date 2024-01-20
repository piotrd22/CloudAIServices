terraform {
  required_providers {
    awscc = {
      source  = "hashicorp/awscc"
      version = "0.66.0"
    }
    aws = {
      source  = "hashicorp/aws"
      version = "5.30.0"
    }
  }
}

provider "awscc" {
  region = "eu-west-1"
}

provider "aws" {
  region = "eu-west-1"
}

locals {
  student_prefix          = "pdamrych"
  forecast_execution_role = "arn:aws:iam::063518513702:role/service-role/AmazonForecast-ExecutionRole-1701791846373"
}
