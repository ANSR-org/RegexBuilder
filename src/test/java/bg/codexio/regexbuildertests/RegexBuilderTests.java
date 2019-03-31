package bg.codexio.regexbuildertests;

import bg.codexio.regexbuilder.RegexBuilder;
import bg.codexio.regexbuilder.RegexBuilderImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;

public class RegexBuilderTests {

    @Test
    public void testStandardMatches_digit() {
        final String target = "This is a 1 test string!";

        MatchResult result = RegexBuilder.create().findDigits().toPattern().matcher(target).results()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(result, "No matches found!");
        Assertions.assertEquals("1", result.group(), "Only one digit is there");
    }

    @Test
    public void testStandardMatches_digits() {
        final String target = "This is a 100 test string!";

        MatchResult result = RegexBuilder.create().findDigits().toPattern().matcher(target).results()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(result, "No matches found!");
        Assertions.assertEquals("100", result.group());
    }

    @Test
    public void testStandardMatches_single_digit() {
        final String target = "This is a 150 test string!";

        MatchResult result = RegexBuilder.create().findSingleDigit().toPattern().matcher(target).results()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(result, "No matches found!");
        Assertions.assertEquals("1", result.group(), "Only one digit is there");
    }

    @Test
    public void testStandardMatches_words() {
        final String target = "This is a 150 test string!";

        MatchResult result = RegexBuilder.create().findPossibleWordCharacters().toPattern().matcher(target).results()
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(result, "No matches found!");
        Assertions.assertEquals("This", result.group(), "First word is 'This'");
    }

    @Test
    public void testComplexPattern() {
        //On each input line you’ll be given data in format: "singer @venue ticketsPrice ticketsCount".
        // (.*?)@(.*?) (\d+) (\d+)

        final String target = "Lepa Brena @Sunny Beach 25 3500";

        RegexBuilderImpl regexBuilder = RegexBuilder.create()
                .startGroup("singer")
                    .findEverythingUntil()
                .endGroup()
                .findSpecialPattern(" @")
                .startGroup("venue")
                    .findEverythingUntil()
                .endGroup()
                .findSingleSymbol(' ')
                .startGroup("price")
                    .findDigits()
                .endGroup()
                .findSingleSymbol(' ')
                .startGroup("ticketCount")
                    .findDigits()
                .endGroup();

        final String expectedPattern = "(?<singer>.*?) @(?<venue>.*?)\\ (?<price>\\d+)\\ (?<ticketCount>\\d+)";
        final String actualPattern = regexBuilder.toString();
        Assertions.assertEquals(expectedPattern, actualPattern);

        Matcher matcher = regexBuilder.toPattern().matcher(target);
        Assertions.assertTrue(matcher.find());
        Assertions.assertEquals("Lepa Brena", matcher.group("singer"));
        Assertions.assertEquals("Sunny Beach", matcher.group("venue"));
        Assertions.assertEquals(25, Integer.parseInt(matcher.group("price")));
        Assertions.assertEquals(3500, Integer.parseInt(matcher.group("ticketCount")));
    }

    @Test
    public void testComplexPattern_characterClass() {
        //On each input line you’ll be given data in format: "singer @venue ticketsPrice ticketsCount".
        // (.*?)@(.*?) (\d+) (\d+)

        final String target = "Lepa Brena @Sunny Beach 25 3500";

        RegexBuilderImpl regexBuilder = RegexBuilder.create()
                .startGroup("singer")
                    .startCharacterClass()
                        .range('a', 'z')
                        .range('A', 'Z')
                        .findSingleSymbol(' ')
                    .endCharacterClass()
                    .areMany()
                .endGroup()
                .findSpecialPattern(" @")
                .startGroup("venue")
                .findEverythingUntil()
                .endGroup()
                .findSingleSymbol(' ')
                .startGroup("price")
                .findDigits()
                .endGroup()
                .findSingleSymbol(' ')
                .startGroup("ticketCount")
                .findDigits()
                .endGroup();

        final String expectedPattern = "(?<singer>[a-zA-Z\\ ]+) @(?<venue>.*?)\\ (?<price>\\d+)\\ (?<ticketCount>\\d+)";
        final String actualPattern = regexBuilder.toString();
        Assertions.assertEquals(expectedPattern, actualPattern);

        Matcher matcher = regexBuilder.toPattern().matcher(target);
        Assertions.assertTrue(matcher.find());
        Assertions.assertEquals("Lepa Brena", matcher.group("singer"));
        Assertions.assertEquals("Sunny Beach", matcher.group("venue"));
        Assertions.assertEquals(25, Integer.parseInt(matcher.group("price")));
        Assertions.assertEquals(3500, Integer.parseInt(matcher.group("ticketCount")));
    }
}
