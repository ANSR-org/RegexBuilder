package bg.codexio.regexbuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class AbstractRegexBuilder<T extends AbstractRegexBuilder, U> implements RegexBuilder<T> {

    protected StringBuilder pattern;
    protected U initiator;

    public AbstractRegexBuilder() {
        this.pattern = new StringBuilder();
    }

    public AbstractRegexBuilder(StringBuilder pattern) {
        this.pattern = pattern;
    }

    public T findSingleDigit() {
        return this.newInstance(this.pattern.append("\\d"));
    }

    public T findPossibleDigits() {
        return this.newInstance(this.pattern.append("\\d*"));
    }

    public T findDigits() {
        return this.newInstance(this.pattern.append("\\d+"));
    }

    public T findSingleWordCharacter() {
        return this.newInstance(this.pattern.append("\\w"));
    }

    public T findPossibleWordCharacters() {
        return this.newInstance(this.pattern.append("\\w*"));
    }

    public T findWordCharacters() {
        return this.newInstance(this.pattern.append("\\w+"));
    }

    public T findSingleSymbol(Character symbol) {
        return this.newInstance(this.pattern.append("\\").append(symbol));
    }

    public T findSpecialPattern(String pattern) {
        return this.newInstance(this.pattern.append(pattern));
    }

    public T findManySymbols(Character symbol) {
        return this.newInstance(this.pattern.append("\\").append(symbol).append("+"));
    }

    public T findPossibleSymbols(Character symbol) {
        return this.newInstance(this.pattern.append("\\").append(symbol).append("*"));
    }

    public T findSymbol(Character symbol, int miminumOccurrences) {
        return this.newInstance(this.pattern.append("\\").append(symbol).append("{").append(miminumOccurrences).append(",}"));
    }

    public T findSymbol(Character symbol, int miminumOccurrences, int maximumOccurrences) {
        return this.newInstance(this.pattern.append("\\").append(symbol).append("{").append(miminumOccurrences).append(",").append(maximumOccurrences).append("}"));
    }

    public T findSymbolExact(Character symbol, int exactOccurrences) {
        return this.newInstance(this.pattern.append("\\").append(symbol).append("{").append(exactOccurrences).append("}"));
    }

    public T findExactTimes(int exactOccurrences) {
        return this.newInstance(this.pattern.append("{").append(exactOccurrences).append("}"));
    }

    public T setLimit(int minimumOccurrences) {
        return this.newInstance(this.pattern.append("{").append(minimumOccurrences).append(",}"));
    }

    public T setLimit(int minimumOccurrences, int maximumOccurrences) {
        return this.newInstance(this.pattern.append("{").append(minimumOccurrences).append(",").append(maximumOccurrences).append("}"));
    }

    public T areMany() {
        return this.newInstance(this.pattern.append("+"));
    }

    public T isPossible() {
        return this.newInstance(this.pattern.append("*"));
    }

    public T findEverythingUntil() {
        return this.newInstance(this.pattern.append(".*?"));
    }

    public T without(String without) {
        return this.newInstance(this.pattern.append("^").append(without));
    }

    @Override
    public String toString() {
        return this.pattern.toString();
    }

    public Pattern toPattern() {
        return Pattern.compile(this.pattern.toString());
    }

    protected T newInstance(StringBuilder pattern) {
        try {
            Constructor ctor = Arrays.stream(this.getClass().getDeclaredConstructors())
                    .filter(c -> c.getParameters().length == 2)
                    .filter(c -> Arrays.stream(c.getParameters()).anyMatch(p -> p.getType() == StringBuilder.class))
                    .filter(c -> Arrays.stream(c.getParameters()).anyMatch(p -> p.getType().getName().contains("bg.codexio.regexbuilder")))
                    .findFirst()
                    .orElse(null);

            if (ctor != null) {
                return (T)ctor.newInstance(pattern, this.initiator);
            }

            ctor = Arrays.stream(this.getClass().getDeclaredConstructors())
                    .filter(c -> c.getParameters().length == 1)
                    .filter(c -> c.getParameters()[0].getType() == StringBuilder.class)
                    .findFirst()
                    .orElse(null);

            return (T)ctor.newInstance(pattern);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
