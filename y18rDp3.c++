#include <iostream>
#include <bits/stdc++.h>
#define ll long long
using namespace std;

int row, col, w;
ll rght[105][105][105], down[105][105][105], sumr[105][105], sumd[105][105];
int grid[105][105];

class Node{
    public:
    
    int count;
    Node* child[26];

    Node(){
        count = 0;
        
        for(int i = 0; i < 26; i++)
            child[i] = NULL;
    }

    void insert(string word, int index){
        if(index == word.size()){
            count++;
            return;
        }

        int ch = word[index] - 'A';

        if(child[ch] == NULL)
            child[ch] = new Node();

        child[ch]->insert(word, index+1);
    }
};

Node* root;

void fillRightDown(){
    for(int i = 0; i < row; i++){
        for(int j = 0; j < col; j++){

            Node* curr = root;

            ll prev = 0;
            for(int k = 1; k <= j + 1; k++){
                rght[i][j][k] = prev;

                if(curr != NULL && curr->child[grid[i][j - k + 1]] != NULL){
                    curr = curr->child[grid[i][j - k + 1]];

                    rght[i][j][k] += (curr->count * k);
                }
                else
                    curr = NULL;

                prev = rght[i][j][k];
                
                if(i > 0)
                    rght[i][j][k] += rght[i - 1][j][k];
            }

            curr = root;
            prev = 0;

            for(int k = 1; k <= i + 1; k++){
                down[i][j][k] = prev;

                if(curr != NULL && curr->child[grid[i - k + 1][j]] != NULL){
                    curr = curr->child[grid[i - k + 1][j]];

                    down[i][j][k] += (curr->count * k);
                }
                else
                    curr = NULL;

                prev = down[i][j][k];

                if(j > 0)
                    down[i][j][k] += down[i][j - 1][k];
            }
        }
    }
}

class Fraction{
    int customgcd(int a, int b){
        if(b == 0)
            return a;
        return customgcd(b, a % b);
    }
    
    public:
    ll num, deno;

    Fraction(int num, int deno){
        this->num = num;
        this->deno = deno;   

        if(num > 0){
            int gcd = customgcd(num, deno);
            this->num /= gcd;
            this->deno /= gcd;
        }
        else
            this->deno = 1; 
    }
};

int cmpare(Fraction* fraction1, Fraction* fraction2){
    if(fraction1->num == fraction2->num && fraction1->deno == fraction2->deno)
        return 0;
    
    if(fraction1->num * fraction2->deno < fraction2->num * fraction1->deno)
        return -1;
        
    return 1;
}

void emptyNode(Node* node){
    for(int i = 0; i < 26; i++){
        if(node->child[i] != NULL)
            emptyNode(node->child[i]);
    }
    
    delete node;
}

void solve(){
    cin>>row>>col>>w;

    root = new Node();

    for(int i = 0; i < row; i++){
        string line;
        cin>>line;
        
        for(int j = 0; j < col; j++)
            grid[i][j] = line[j] - 'A';
    }
    
    for(int i = 0; i < w; i++){
        string word;
        cin>>word;

        if(word.size() <= max(row, col)){
            root->insert(word, 0);
            
            reverse(word.begin(), word.end()); 
            
            root->insert(word, 0);
        }
    }
    
    fillRightDown();

    Fraction* answer = new Fraction(0, 1);
    ll freq = 0;

    for(int i1 = 0; i1 < row; i1++){
        for(int j1 = 0; j1 < col; j1++){
            for(int i2 = i1; i2 < row; i2++){
                for(int j2 = j1; j2 < col; j2++){
                    int width = j2 - j1 + 1;

                    sumr[i2][j2] = rght[i2][j2][width];
                    
                    if(i1 > 0)
                        sumr[i2][j2] -= rght[i1 - 1][j2][width];

                    if(j2 > j1)
                        sumr[i2][j2] += sumr[i2][j2 - 1];

                    int height = i2 - i1 + 1;

                    sumd[i2][j2] = down[i2][j2][height];
                    
                    if(j1 > 0)
                        sumd[i2][j2] -= down[i2][j1 - 1][height];

                    if(i2 > i1)
                        sumd[i2][j2] += sumd[i2 - 1][j2];

                    ll sum = sumr[i2][j2] + sumd[i2][j2];

                    Fraction* fraction = new Fraction(sum, height + width);

                    int compare = cmpare(answer, fraction);

                    if(compare < 0){
                        answer->num = fraction->num;
                        answer->deno = fraction->deno;
                        freq = 1;
                    }
                    else if(compare == 0)
                        freq++;
                        
                    delete fraction;
                }
            }
        }
    }

    cout<<answer->num<<"/"<<answer->deno<<" "<<freq<<'\n';
    
    emptyNode(root);
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin>>t;
    
    for(int i = 1; i <= t; i++){
        cout<<"Case #"<<i<<": ";
        solve();
    }
   
    return 0;
}