package bg.codexio.regexbuilder;

public class GroupCharacterClassBuilder extends AbstractRegexBuilder<GroupCharacterClassBuilder, GroupBuilder> {

    public GroupCharacterClassBuilder(StringBuilder pattern, GroupBuilder initiator) {
        super(pattern);
        this.initiator = initiator;
    }

    public GroupCharacterClassBuilder range(Character from, Character to) {
        return new GroupCharacterClassBuilder(this.pattern.append(from).append("-").append(to), this.initiator);
    }

    public GroupBuilder endCharacterClass() {
        this.pattern.append("]");
        return this.initiator;
    }
}
