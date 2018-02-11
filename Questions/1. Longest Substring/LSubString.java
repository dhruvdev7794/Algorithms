public class LSubString {
	public static void main(String [] args) {
		System.out.println(longestSubstring("ohvhjdml"));
		System.out.println(longestSubstring("abcabcbb"));
		System.out.println(longestSubstring("bbbbb"));
		System.out.println(longestSubstring("pwwkew"));
	}
	
	
	public static String longestSubstring(String s) {
        String tempStr = "", greatestStr = "";
		for (int i = 0 ; i< s.length(); i++) {
			if(tempStr.indexOf(s.charAt(i)) == -1) {
				tempStr+=s.charAt(i);
			}
			else {
				tempStr = tempStr.substring(tempStr.indexOf(s.charAt(i)) + 1) + s.charAt(i);
			}
			if(tempStr.length()>greatestStr.length()) {
				greatestStr = tempStr;
			}
		}

        return greatestStr;
    }
}