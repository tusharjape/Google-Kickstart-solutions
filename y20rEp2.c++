// Kickstart 2020 E 
// Problem 2 - High Buildings
#include<bits/stdc++.h>
using namespace std;

void solve(){
	int n, a, b, c;	cin>>n>>a>>b>>c;
	if(n==1){
		if(a==b and b==c and c==1)	cout<<"1\n";
		else	cout<<"IMPOSSIBLE\n";
		return;
	}
	else if(n==2){
		if(c==1 and a==2 and b==1)		cout<<"1 2\n";
		else if(c==1 and a==1 and b==2)	cout<<"2 1\n";
		else if(c==2 and a==2 and b==2)	cout<<"1 1\n";
		else cout<<"IMPOSSIBLE\n";
		return;
	}
	int aExceptMax = a-c;
	int bExceptMax = b-c;
	int one = n-(a+b-c);
	if(aExceptMax+bExceptMax+c > n or a+b==2){
		cout<<"IMPOSSIBLE\n";
		return;
	}
	if(aExceptMax>0){
		for(int i=0; i<aExceptMax; i++)	cout<<"2 ";
		for(int i=0; i<one; i++)	cout<<"1 ";
		for(int i=0; i<c; i++)		cout<<"3 ";
		for(int i=0; i<bExceptMax; i++)	cout<<"2 ";		
	}
	else if(bExceptMax>0){
		for(int i=0; i<c; i++)		cout<<"3 ";
		for(int i=0; i<one; i++)	cout<<"1 ";
		for(int i=0; i<bExceptMax; i++)	cout<<"2 ";
	}
	else if(c>=2){
		cout<<"3 ";
		for(int i=0; i<one; i++)	cout<<"1 ";
		for(int i=0; i<n-1-one; i++)cout<<"3 ";
	}
	else	cout<<"IMPOSSIBLE";
	cout<<"\n";
}

int main(){
    int t;  cin>>t;
    for(int i=1; i<=t; i++){
        cout<<"Case #"<<i<<": ";
        solve();
    }
}