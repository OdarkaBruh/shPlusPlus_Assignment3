import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
from scipy import stats

# read text file into pandas DataFrame
df = pd.read_csv("../result.txt", sep=",", header=None).astype(int).T
df.columns = ['values']
dfSubset = df.value_counts().reset_index().dropna(how='all')
dfSubset.columns = ['values', "count"]

#dfSubset2 = pd.concat([df, dfSubset[dfSubset['count']>10]]).dropna()
dfSubset2 = df.loc[df['values'].isin(dfSubset[dfSubset['count'] > 100]['values'])]
print(dfSubset.tail())
def plotGraph():
    plt.plot(df['values'], df['count'])
    plt.title("How many times does a certain amount occur")
    plt.xlabel("Amount of money")
    plt.ylabel("Times it occurred")
    plt.show()
#plotGraph()

def plotHist():
    fig, ax1 = plt.subplots()
    sns.kdeplot(data=df, x="values", ax=ax1)
    ax1.set_xlim((df["values"].min(), df["values"].max()))
    ax2 = ax1.twinx()
    sns.histplot(data=df, x="values", discrete=True, ax=ax2)
print(len(df))
plotHist()
print()
print("Mean: " + str(df['values'].mean()))
print("Median: " + str(df['values'].median()))
print("Mode: " + str(df['values'].mode()))