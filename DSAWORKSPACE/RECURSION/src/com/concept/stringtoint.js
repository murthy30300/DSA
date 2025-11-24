/**
 * @param {string} s
 * @return {number}
 * Example 1:

Input: s = "42"

Output: 42

Explanation:

The underlined characters are what is read in and the caret is the current reader position.
Step 1: "42" (no characters read because there is no leading whitespace)
         ^
Step 2: "42" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "42" ("42" is read in)
           ^
Example 2:

Input: s = " -042"

Output: -42

Explanation:

Step 1: "   -042" (leading whitespace is read and ignored)
            ^
Step 2: "   -042" ('-' is read, so the result should be negative)
             ^
Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
               ^
Example 3:

Input: s = "1337c0d3"

Output: 1337

Explanation:

Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
         ^
Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
             ^
Example 4:

Input: s = "0-1"

Output: 0

Explanation:

Step 1: "0-1" (no characters read because there is no leading whitespace)
         ^
Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
         ^
Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
          ^
Example 5:

Input: s = "words and 987"

Output: 0

Explanation:

Reading stops at the first non-digit character 'w'.
 */
var myAtoi = function (s) {
    if (s.length === 0)
        return 0;
    let i = 0;
    while (s[i] === ' ') {
        i++;
    }
    let sign = 1, res = 0;
    //  s[i] === '-'?sign =1:sign=2;
    // const sign = (s[i] === '-') ? 1 : 2;
    if (s[i] === '-') {
        sign = -1;
        i = i + 1;
    }
    else if (s[i] === '+') {
        // sign = 2;
        i = i + 1;
    }
    // if (s[i] < '0' && s[i] > '9') {
    //     return 0;
    // }
    if (i < s.length && s[i] >= '0' && s[i] <= '9') {
        while (i < s.length && s[i] >= '0' && s[i] <= '9') {
            res = res * 10 + (s[i] - '0')
            i = i + 1;
        }
    }
    else {
        return 0;
    }
    res = res * sign;

    const INT_MIN = - (2 ** 31);
    const INT_MAX = 2 ** 31 - 1;
    if (res < INT_MIN) {
        return INT_MIN;
    }
    if (res > INT_MAX) {
        return INT_MAX;
    }
    return res;
};
console.log(myAtoi("99words and 987"))