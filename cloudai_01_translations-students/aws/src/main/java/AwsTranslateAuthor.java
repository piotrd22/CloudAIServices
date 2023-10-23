class AwsTranslateAuthor {

    static final String AUTHOR = """
            <dl><dt>Ur.</dt><dd> 24 grudnia 1798 r. w Zaosiu koło Nowogródka<br>
            </dd><dt>Zm.</dt><dd> 26 listopada 1855 r. w Konstantynopolu (dziś: Stambuł)<br>
            </dd><dt>Najważniejsze dzieła: </dt><dd> <i>Ballady i romanse</i> (1822), <i>Grażyna</i> (1823), <i>Sonety krymskie</i> (1826), <i>Konrad Wallenrod</i> (1828), <i>Dziady</i> (cz.II i IV 1823, cz.III 1832), <i>Księgi narodu polskiego i pielgrzymstwa polskiego</i> (1833), <i>Pan Tadeusz</i> (1834); wiersze: <i>Oda do młodości</i> (1820), <i>Do Matki Polki</i> (1830), <i>Śmierć pułkownika</i> (1831), <i>Reduta Ordona</i> (1831)<br>

            </dd></dl>
            <p>Polski poeta i publicysta okresu romantyzmu (czołowy z trójcy "wieszczów"). Syn adwokata, Mikołaja (zm. 1812) herbu Poraj oraz Barbary z Majewskich. Ukończył studia na Wydziale Literatury Uniwersytetu Wileńskiego; stypendium odpracowywał potem jako nauczyciel w Kownie. Był współzałożycielem tajnego samokształceniowego Towarzystwa Filomatów (1817), za co został w 1823 r. aresztowany i skazany na osiedlenie w głębi Rosji. W latach 1824-1829 przebywał w Petersburgu, Moskwie i na Krymie; następnie na emigracji w Paryżu. Wykładał literaturę łacińską na Akademii w Lozannie (1839), a od 1840 r. literaturę słowiańską w College de France w Paryżu. W 1841 r. związał się z ruchem religijnym A. Towiańskiego. W okresie Wiosny Ludów był redaktorem naczelnym fr. dziennika&nbsp;»Trybuna Ludów«&nbsp;i organizatorem ochotniczego Zastępu Polskiego, dla którego napisał demokratyczny <i>Skład zasad</i>.<br>
            </p>
            <ul class="meta"><li>autor: Cezary Ryska</li></ul>
            """;

    final AwsTranslate translator;

    AwsTranslateAuthor(AwsTranslate translator) {
        this.translator = translator;
    }

    String translate(String targetLang) {
        return translator.translate(AUTHOR.replace("<i>", "<i translate=\"no\">"), "pl", targetLang);
    }
}
