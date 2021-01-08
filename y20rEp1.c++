// Kickstart 2020 E 
// Problem 1 - Longest Arithmetic
#include<bits/stdc++.h>
using namespace std;

int helper(vector<int>& arr, int n){
    int prevDiff = arr[1]-arr[0], currLen = 2, maxLen = 2;
    for(int i=2; i<n; i++){
        int currDiff = arr[i] - arr[i-1];
        if(currDiff == prevDiff)    currLen++;
        else    {prevDiff = currDiff; currLen = 2;}
        maxLen = max(maxLen, currLen);
    }
    return maxLen;
}

int main(){
    int t;  cin>>t;
    for(int i=1; i<=t; i++){
        int n;  cin>>n;
        vector<int> arr(n);
        for(int& e: arr)    cin>>e;
        cout<<"Case #"<<i<<": "<<helper(arr,n)<<"\n";
    }
    return 0;
}