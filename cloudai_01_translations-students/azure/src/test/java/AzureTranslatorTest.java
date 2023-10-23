import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AzureTranslatorTest {

    AzureTranslator underTest = new AzureTranslator();

    @Test
    void shouldTranslateWithAzureTranslate() {
        // given
        String input = "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.";

        // when
        String output = underTest.translate(input, "fr");

        // then
        assertThat(output).containsAnyOf("faiblesse", "toujours");
    }
}
