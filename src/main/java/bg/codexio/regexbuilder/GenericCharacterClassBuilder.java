package bg.codexio.regexbuilder;

public class GenericCharacterClassBuilder extends AbstractRegexBuilder<GenericCharacterClassBuilder, RegexBuilderImpl> {

    public GenericCharacterClassBuilder(StringBuilder pattern, RegexBuilderImpl initiator) {
        super(pattern);
        this.initiator = initiator;
    }

    public GenericCharacterClassBuilder range(Character from, Character to) {
        return new GenericCharacterClassBuilder(this.pattern.append(from).append("-").append(to), this.initiator);
    }

    public RegexBuilderImpl endCharacterClass() {
        this.pattern.append("]");
        return this.initiator;
    }
}
