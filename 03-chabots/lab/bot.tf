resource "aws_lex_bot" "order_flowers" {

  name = "${local.student_prefix}_OrderFlowers"
  description = "Bot to order flowers on the behalf of a user"
  child_directed = false
  detect_sentiment = false
  idle_session_ttl_in_seconds = 600
  locale = "en-US"
  process_behavior = "BUILD"
  voice_id = "Salli"

  clarification_prompt {
    max_attempts = 2

    message {
      content_type = "PlainText"
      content      = "I didn't understand you, what would you like to do?"
    }
  }

  abort_statement {
    message {
      content_type = "PlainText"
      content      = "Sorry, I am not able to assist at this time"
    }
  }

  intent {
    intent_name    = aws_lex_intent.order_flowers.name
    intent_version = aws_lex_intent.order_flowers.version
  }

}
