"""Implement a method to perform basic string compression using the counts of repeated characters. 
For example, the string aabcccccddd would become a2b1c5d3. 
If the "compressed" string would not become smaller than the original string, 
your method should return the original string."""

def compress_string(s):
    res = ""
    i = 0
    while i < len(s):
        res += s[i]
        count = 1
        while (i < len(s) - 1 and s[i] == s[i + 1]):
            count += 1
            i += 1
        res += str(count)
        i += 1
    if len(res) >= len(s):
        res = s
    return res    
  
  
# or 
def string_compress(s):
    result = ""
    cnt = 1
    for i in range(len(s) - 1):
        if s[i] == s[i + 1]:
            cnt += 1
        else:
            result += s[i] + str(cnt)
            cnt = 1
    result += s[i+1] + str(cnt)    #important to use i + 1
    if len(result) >= len(s):
         return s
    return result

if __name__ == "__main__":
  assert string_compress("aabcccccddd") == "a2b1c5d3"
  assert string_compress("aabcccccddde") == "a2b1c5d3e1"
  assert string_compress("abcde") == "abcde"
