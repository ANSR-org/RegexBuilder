package bg.codexio.regexbuilder;

public class GroupBuilder extends AbstractRegexBuilder<GroupBuilder, RegexBuilderImpl> {

    public GroupBuilder(StringBuilder pattern, RegexBuilderImpl initiator) {
        super(pattern);
        this.initiator = initiator;
    }

    public GroupBuilder(StringBuilder pattern, String name, RegexBuilderImpl initiator) {
        super(pattern);
        this.pattern.append("?<").append(name).append(">");
        this.initiator = initiator;
    }

    public GroupCharacterClassBuilder startCharacterClass() {
        return new GroupCharacterClassBuilder(this.pattern.append("["), this);
    }

    public RegexBuilderImpl endGroup() {
        this.pattern.append(")");
        return this.initiator;
    }
}