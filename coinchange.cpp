//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution {
  public:
    int fun(int idx, int amount, vector<int> &coins, vector<vector<int>> &dp)
    {
        if(idx==0)
        {
            return ((amount%coins[0])==0);
        }

        if(dp[idx][amount]!=-1) return dp[idx][amount];

        int nttaken=fun(idx-1,amount,coins,dp);

        int taken=0;
        if(coins[idx]<=amount)
        {
            taken=fun(idx,amount-coins[idx],coins,dp);
        }

        return dp[idx][amount]=taken+nttaken;
    }
    
    int count(vector<int>& coins, int sum) {
        // code here.
        int n=coins.size();
        vector<vector<int>> dp(n, vector<int> (sum+1,-1));

        return fun(n-1, sum, coins, dp);
    }
};

//{ Driver Code Starts.

int main() {

    int t;
    cin >> t;
    cin.ignore();
    while (t--) {
        vector<int> arr;
        string input;
        getline(cin, input);
        stringstream ss(input);
        int number;
        while (ss >> number) {
            arr.push_back(number);
        }
        int sum;
        cin >> sum;
        cin.ignore();
        Solution ob;
        cout << ob.count(arr, sum) << endl;
        cout << "~" << endl;
    }

    return 0;
}
