resource "awscc_forecast_dataset_group" "trends_dataset_group" {
  dataset_group_name = "${local.student_prefix}_trends_group"
  domain = "CUSTOM"
  dataset_arns = [ awscc_forecast_dataset.trends_dataset.arn ]
}

resource "awscc_forecast_dataset" "trends_dataset" {
  dataset_name = "${local.student_prefix}_trends"
  domain = "CUSTOM"
  data_frequency = "D"
  dataset_type = "TARGET_TIME_SERIES"
  schema = {
    attributes = [
      {
        attribute_name = "item_id"
        attribute_type = "string"
      },
      {
        attribute_name = "timestamp"
        attribute_type = "timestamp"
      },
      {
        attribute_name = "target_value"
        attribute_type = "float"
      }
    ]

  }
}

output "trends_dataset_group_arn" {
  value = awscc_forecast_dataset_group.trends_dataset_group.dataset_group_arn
}

output "trends_dataset_arn" {
    value = awscc_forecast_dataset.trends_dataset.arn
}

output "student_prefix" {
  value = "${local.student_prefix}"
}

output "forecast_execution_role" {
  value = "${local.forecast_execution_role}"
}
