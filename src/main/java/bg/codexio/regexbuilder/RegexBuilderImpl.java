package bg.codexio.regexbuilder;

public class RegexBuilderImpl extends AbstractRegexBuilder<RegexBuilderImpl, RegexBuilderImpl> {

    public RegexBuilderImpl() {
    }

    public RegexBuilderImpl(StringBuilder pattern) {
        super(pattern);
    }

    public GenericCharacterClassBuilder startCharacterClass() {
        return new GenericCharacterClassBuilder(this.pattern.append("["), this);
    }

    public GroupBuilder startGroup() {
        return new GroupBuilder(this.pattern.append("("), this);
    }

    public GroupBuilder startGroup(String name) {
        return new GroupBuilder(this.pattern.append("("), name, this);
    }
}
