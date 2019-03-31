package bg.codexio.regexbuilder;

import java.util.regex.Pattern;

public interface RegexBuilder<T extends RegexBuilder> {

    static RegexBuilderImpl create() {
        return new RegexBuilderImpl();
    }

    /**
     * Finds a single digit (e.g. \d)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSingleDigit();

    /**
     * Finds zero or more digits (e.g. \d*)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findPossibleDigits();

    /**
     * Finds one or more digits (e.g. \d+)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findDigits();

    /**
     * Finds a single word character (e.g. \w)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSingleWordCharacter();

    /**
     * Finds zero or more word characters (e.g. \w*)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findPossibleWordCharacters();

    /**
     * Finds one or more word characters (e.g. \w+)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findWordCharacters();

    /**
     * Finds a specified symbol as a character
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSingleSymbol(Character symbol);

    /**
     * Finds a specified pattern as a string
     * It means it does not escape symbols
     * If there are special symbols they will be treat as such
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSpecialPattern(String pattern);

    /**
     * Finds one or more specified symbols as a character (symbol+)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findManySymbols(Character symbol);

    /**
     * Finds zero or more specified symbols as a character (symbol*)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findPossibleSymbols(Character symbol);

    /**
     * Finds specified symbol minimum times (e.g. z{3,})
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSymbol(Character symbol, int miminumOccurrences);

    /**
     * Finds specified symbol limited times (e.g. z{3,8})
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSymbol(Character symbol, int miminumOccurrences, int maximumOccurrences);

    /**
     * Finds specified symbol exact times (e.g. z{3})
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findSymbolExact(Character symbol, int exactOccurrences);

    /**
     * Adds to the pattern so far a limitation (e.g. {3})
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findExactTimes(int exactOccurrences);

    /**
     * Adds to the pattern so far a minimum occurrence factor (e.g. {3,})
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T setLimit(int minimumOccurrences);

    /**
     * Adds to the pattern so far a strict limit (e.g. {3,8})
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T setLimit(int minimumOccurrences, int maximumOccurrences);

    /**
     * Adds to the last pattern (e.g. character group, single symbol, etc...)
     * a restriction of many occurrences (one or many) e.g. "+"
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T areMany();

    /**
     * Adds to the last pattern (e.g. character group, single symbol, etc...)
     * a restriction of possible occurrences (zero or many) e.g. "*"
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T isPossible();

    /**
     * Adds lazy pattern for everything until the next pattern included
     * e.g. .*?
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T findEverythingUntil();

    /**
     * Excludes the next pattern (single symbol or character class)
     *
     * @return an instance of RegexBuilder (outer or inner implementations)
     */
    T without(String without);

    /**
     * Transforms the pattern so far as a string
     *
     * @return String
     */
    String toString();

    /**
     * Compiles the pattern so far to java.util.regex.Pattern
     *
     * @return java.util.regex.Pattern
     */
    Pattern toPattern();
}
