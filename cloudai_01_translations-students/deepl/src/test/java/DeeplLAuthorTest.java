import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeeplLAuthorTest {

    DeepL translator = new DeepL();

    DeepLAuthor underTest = new DeepLAuthor(translator);

    @Test
    void test() {
        // given
        String targetLang = "en";

        // when
        String output = underTest.translate(targetLang);

        // then
        assertThat(output).contains("December", "November", "Ballady i romanse");
    }
}
