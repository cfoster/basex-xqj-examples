module namespace eg = "http://www.example.com";

declare function eg:contains-any-of
  ($arg as xs:string?,
  $searchStrings as xs:string*) as xs:boolean
{
  some $searchString in $searchStrings
  satisfies fn:contains($arg,$searchString)
};

declare function eg:word-count
  ($arg as xs:string?) as xs:integer
{
  fn:count(fn:tokenize($arg, '\W+')[. != ''])
};

declare function eg:multiply
  ($a as xs:float, $b as xs:float) as xs:float
{
  $a * $b
};
