// leetcode question link : https://leetcode.com/problems/edit-distance/description/

class Solution {
public:
    int minDistance(string st1, string st2) {
        int n=st1.length();
        int m=st2.length();

        vector<vector<int>> dp(n, vector<int> (m, -1));
        return fun(n-1, m-1, st1, st2, dp);   
    }

    int fun(int i,int j, string &st1, string &st2, vector<vector<int>> &dp)
    {
        if(i<0) return j+1;
        if(j<0) return i+1;

        if(dp[i][j]!=-1) return dp[i][j];

        if(st1[i]==st2[j]) return dp[i][j]=fun(i-1,j-1,st1,st2,dp);
        return dp[i][j]=1+min(fun(i-1, j, st1, st2, dp), min(fun(i, j-1, st1, st2, dp), fun(i-1, j-1, st1, st2, dp)));
    }
};