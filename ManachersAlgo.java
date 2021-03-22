package Phase2.LC;

public class ManachersAlgo {

    public static void main(String args[]){
  //      System.out.println(longestPalindromicSubstring("abaxabaxabybaxabyb"));
        System.out.println(longestPalindromicSubstring("babad"));

  //      System.out.println(longestPalindromicSubstring("cbbd"));
        // System.out.println(longestPalindromicSubstring("@c@b@b@d@"));

    }

    // odd length palindrome
    public static int longestPalindromicSubstring(String str){

        // len<2 , whole string is palidrome
        if(str.length()<2){
            return str.length();
        }
        if(str.length()==2){
            return str.charAt(0)==str.charAt(1)?2:0;
        }

        // character array
        char arr[]=str.toCharArray();

        // prefix array
        int prefix[]=new int[arr.length];
        prefix[0]=1;
        prefix[1]=arr[0]==arr[2]?3:1;

        int longestPalindromeLength =prefix[1];
        int index=1;

        // current max palindrome ki left boundary
        int leftBoundary=index - (longestPalindromeLength)/2 ;
        // current max palindrome ki right boundary
        int rightBoundary=index + (longestPalindromeLength)/2;

        for(int i=2;i<prefix.length;i++) {
            int gapFromPalin = i - index;

            // no mirror palindrome , beyond left half, this is the new center
            if (index - gapFromPalin < 0) {
                int leftTemp = i - 1;
                int rightTemp = i + 1;

                while (leftTemp >= 0 && rightTemp < arr.length && arr[leftTemp] == arr[rightTemp]) {
                    leftTemp--;
                    rightTemp++;
                }

                prefix[i] = rightTemp - leftTemp - 1;

                if (longestPalindromeLength < prefix[i]) {
                    longestPalindromeLength = prefix[i];
                    index = i;
                    leftBoundary = leftTemp+1;
                    rightBoundary = rightTemp-1;
                }
                if (rightBoundary > arr.length - 1) {
                    break;
                }
            } else {
                int mirrorPalindromeLength = prefix[index - gapFromPalin];
                int mirrorPalindromeIndex = index - gapFromPalin;

                // mirror palindrome exceeds left boundary
                if (mirrorPalindromeIndex>=leftBoundary && mirrorPalindromeIndex - mirrorPalindromeLength / 2 < leftBoundary) {
                    prefix[i] = (mirrorPalindromeIndex - leftBoundary)*2 + 1;
                }

                // contained
                else if ((mirrorPalindromeIndex - mirrorPalindromeLength / 2 > leftBoundary)
                        && (i + mirrorPalindromeLength / 2 < rightBoundary)) {
                    prefix[i] = mirrorPalindromeLength;
                }

                // proper prefix and suffix, expand after this length
                else if ((mirrorPalindromeIndex - mirrorPalindromeLength / 2 == leftBoundary)
                        && (i + mirrorPalindromeLength / 2 == rightBoundary)) {
                    int leftTemp = i - mirrorPalindromeLength / 2 - 1;
                    int rightTemp = i + mirrorPalindromeLength / 2 + 1;


                    while (leftTemp >= 0 && rightTemp < arr.length && arr[leftTemp] == arr[rightTemp]) {
                        leftTemp--;
                        rightTemp++;
                    }

                    prefix[i] = rightTemp - leftTemp - 1;



                    if(longestPalindromeLength<prefix[i]) {
                        leftBoundary = leftTemp+1;
                        rightBoundary = rightTemp-1;
                        longestPalindromeLength = prefix[i];
                        index = i;
                    }

                    if (rightBoundary > arr.length - 1) {
                        break;
                    }
                } else {

                    // expand around that index.( beyond right index)
                    int leftTemp = i - 1;
                    int rightTemp = i + 1;

                    while (leftTemp >= 0 && rightTemp < arr.length && arr[leftTemp] == arr[rightTemp]) {
                        leftTemp--;
                        rightTemp++;
                    }

                    prefix[i] = rightTemp - leftTemp - 1;

                    if (longestPalindromeLength < prefix[i]) {
                        longestPalindromeLength = prefix[i];
                        index = i;
                        leftBoundary = leftTemp +1;
                        rightBoundary = rightTemp-1;
                    }
                    if (rightBoundary > arr.length - 1) {
                        break;
                    }
                }
            }
        }

        int max=-1;
        for(int i:prefix){
            max=Math.max(max,i);
        }
        return max;

    }

}
