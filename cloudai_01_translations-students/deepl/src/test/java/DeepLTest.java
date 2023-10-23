import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeepLTest {

    static final String TEST_INPUT = "Our greatest weakness lies in giving up. The most certain way to succeed is always to try just one more time.";

    DeepL underTest = new DeepL();

    @Test
    public void shouldTranslateWithDeepL() {
        // given
        String targetLang = "fr";

        // when
        String output = underTest.translate(TEST_INPUT, targetLang);

        // then
        assertThat(output).containsAnyOf("faiblesse", "toujours");
    }
}
