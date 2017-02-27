import numpy as np
from scipy.stats import uniform as sp_rand
from sklearn.linear_model import Ridge
from sklearn.grid_search import RandomizedSearchCV
# prepare a uniform distribution to sample for the alpha parameter
data = np.genfromtxt('data.csv',skip_header=1,delimiter=',')

print(__doc__)

# Loading the Digits dataset

digits = data
# To apply an classifier on this data, we need to flatten the image, to
# turn the data in a (samples, feature) matrix:
y = digits [:,[11]]
X = digits [:,[1,2,3,4,5,6,7,8,9,10]]
y = y [:,0]

param_grid = {'alpha': sp_rand()}
# create and fit a ridge regression model, testing random alpha values
model = Ridge()
rsearch = RandomizedSearchCV(estimator=model, param_distributions=param_grid, n_iter=100)
rsearch.fit(X, y)
print(rsearch)
# summarize the results of the random parameter search
print(rsearch.best_score_)
print(rsearch.best_estimator_.alpha)
