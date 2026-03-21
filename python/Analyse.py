import numpy as np
import seaborn as sns
import pandas as pd
import matplotlib.pyplot as plt
from scipy import stats
from scipy.interpolate import interp1d
from scipy.ndimage import gaussian_filter1d
from scipy.stats import alpha, percentileofscore

# read text file into pandas DataFrame
df = pd.read_csv("../result.txt", sep=",", header=None).astype(int).T
df.columns = ['value']
dfSubset = df.value_counts().reset_index().dropna(how='all')
dfSubset.columns = ['value', "amount"]

def plotHist():
    counts, bins, patches = plt.hist(df, bins=30)
    plt.bar_label(patches)
    plt.show()
#plotHist()

def plotPlot():
    subDf = dfSubset[dfSubset["amount"] < np.percentile(dfSubset.amount, 80)]
    print("Percentile 80th: ".format(np.percentile(dfSubset.amount, 80)))
    print("Length went from {} to {}".format(len(dfSubset), len(subDf)))


    plt.plot(subDf['value'], subDf['amount'])
    plt.show()

def plotPlot2():
    fig, ax = plt.subplots()
    subDf = df[df['value'].isin(dfSubset[dfSubset["amount"] > 20]['value'])]

    sns.histplot(data=subDf['value'], ax=ax, bins=30, alpha=0.3 , color='green', linewidth=1)
    plt.xlabel("Amount of money")
    plt.ylabel("Times it occurred")
    plt.title("How many times does a certain amount occur")
    print("Quantile Q1-Q3: {}".format(np.quantile(subDf['value'], [0.25, 0.75])))
    print("Percentile 80th: {}".format(np.percentile(subDf['value'], 80)))
    print("Percentile 99th: {}".format(np.percentile(subDf['value'], 99)))
    #sns.kdeplot(data=subDf['value'], ax=ax, color='blue', linestyle='dashed', linewidth=2)
    plt.show()

plotPlot2()