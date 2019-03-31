# RegexBuilder
A simple abstraction over regular expressions using a builder pattern

You can build your RegEx pattern incrementally using the fluent interface. Keep in mind that it will not stop you of messing the pattern by using quantifiers or special symbols without any reason. 

Check the example:

You are given the task to match strings against the following format `singer @venue ticketsPrice ticketsCount`, where:
- Singer can be everything
- Venue can be everything
- Ticket Price is an integer
- Ticket Count is an integer

You want to extract from the valid strings the four matches into named groups (singer, venue, price and ticketCount).

Here is your code:

```
final String target = "Lepa Brena @Sunny Beach 25 3500";
Matcher matcher = RegexBuilder.create()
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
                .endGroup()
                .toPattern()
                .matcher(target);
if (matcher.find()) {
    System.out.println(matcher.group("singer")); // Lepa Brena
    System.out.println(matcher.group("venue")); // Sunny Beach
    System.out.println(matcher.group("price")); // 25
    System.out.println(matcher.group("ticketCount")); // 35000
} else {
    System.out.println("The string does not match the given pattern");
}    
```
