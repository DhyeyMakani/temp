//{ Driver Code Starts

#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
// User function template for C++

class Solution {
  public:
    bool fun(int idx,int target,vector<int> &arr,vector<vector<int>> &dp)
    {
        if(target==0) return true;
        if(idx==0) return arr[0]==target;
        
        if(dp[idx][target]!=-1) return dp[idx][target];
        
        bool nttaken=fun(idx-1,target,arr,dp);
        
        bool taken=false;
        if(arr[idx]<=target)
        {
            taken=fun(idx-1,target-arr[idx],arr,dp);
        }
        
        return dp[idx][target]=(nttaken || taken);
    }
  
    bool isSubsetSum(vector<int>& arr, int target) {
        // code here
        int n=arr.size();
        vector<vector<int>> dp(n, vector<int> (target+1,-1));
        
        return fun(n-1,target,arr,dp);
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
        if (ob.isSubsetSum(arr, sum))
            cout << "true" << endl;
        else
            cout << "false" << endl;
        cout << "~" << endl;
    }
    return 0;
}

// } Driver Code Ends
