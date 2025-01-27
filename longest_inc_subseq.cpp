class Solution {
public:
    int lengthOfLIS(vector<int>& nums)
    {
        if(nums.size()==0)return 0;
        vector<int> v;
        v.push_back(nums[0]);
        for(int i=1;i<nums.size();i++)
        {
            if(nums[i]>v.back())
            {
                v.push_back(nums[i]);
            }
            else
            {
                int itr=lower_bound(v.begin(),v.end(),nums[i])-v.begin();
                v[itr]=nums[i];
            }
        }
        return v.size();
    }
};
