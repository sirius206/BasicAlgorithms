"""
Use Expectation–maximization (E–M)
Guess some cluster centers
Repeat until converged
E-Step: assign points to the nearest cluster center
M-Step: set the cluster centers to the mean

Pseudocode:
1. Select K initial centroids
REPEAT:
    2. Form K clusters by assigning each observation to its nearest centroid's cluster
    3. Recompute centroids for each cluster
UNTIL centroids do not change

https://jakevdp.github.io/PythonDataScienceHandbook/05.11-k-means.html

Elbow method: plot number of clusters V.S. percentage of variance explained (the ratio of between-group variance to the total variance)
              plot different values of k and calculate the Sum of squared error for each cluster

silhouette analysis: study the separation distance between the resulting clusters
Silhouette coefficients (as these values are referred to as) near +1 indicate that the sample is far away from the neighboring clusters. 
A value of 0 indicates that the sample is on or very close to the decision boundary between two neighboring clusters and 
negative values indicate that those samples might have been assigned to the wrong cluster.
Also from the thickness of the silhouette plot the cluster size can be visualized
~~~~~~~~~
k-means++ is an algorithm for choosing the initial values (or "seeds") for the k-means clustering algorithm. Spread out the k initial cluster centers.

1.Choose one center at random among the data points.
2. For each data point x not chosen yet, compute D(x), the distance between x and the nearest center that has already been chosen.
3. Choose one new data point at random as a new center, using a weighted probability distribution where a point x is chosen with probability proportional to D(x)2.
Repeat Steps 2 and 3 until k centers have been chosen.
Now that the initial centers have been chosen, proceed using standard k-means clustering.
~~~~~~~~~ 
Perform feature scaling first before Kmeans
"""

import random
import numpy as np
from collections import defaultdict
 
def k_means(X, k=5, max_iter=1000):
    """
    Args:
    - X - data matrix
    - k - number of clusters
    - max_iter - maximum iteratations
 
    Returns:
    - clusters - dict mapping cluster centers (tuples) to 
                 observations (list of datapoints, each datapoint is a numpy array)
    """
    # initialize cluster centers by random sample k rows of X
    # random.seed(42)    
    centers = [tuple(pt) for pt in random.sample(list(X), k)]
    for i in range(max_iter):
        # E-Step: assign points to the nearest cluster center
        clusters = defaultdict(list)
        for datapoint in X:
            dists = [np.linalg.norm(datapoint - center) for center in centers]
            center = centers[np.argmin(dists)]
            clusters[center].append(datapoint)
        
        # M-Step: average the cluster datapoints to calculate the new centers
        new_centers = [tuple(np.mean(clusters[center], axis = 0)) for center in centers]
        
        # stop iteration if the centers stop changing
        if set(new_centers) == set(centers):
            break
        # update centers
        centers = new_centers
    return clusters  
  
  
# use the iris dataset as example
from sklearn import datasets
iris = datasets.load_iris()
X = iris.data
 
X_clustered = k_means(X, k=5, max_iter=1000)
X_clustered

 
# Output:
# defaultdict(list,
#             {(5.242857142857143,
#               3.6678571428571423,
#               1.5,
#               0.28214285714285714): [array([5.1, 3.5, 1.4, 0.2]),
#               array([5. , 3.6, 1.4, 0.2]),
#               array([5.4, 3.9, 1.7, 0.4]),
#               array([5. , 3.4, 1.5, 0.2]),
#               array([5.4, 3.7, 1.5, 0.2]),
#               array([5.8, 4. , 1.2, 0.2]),
#               array([5.7, 4.4, 1.5, 0.4]),
#               array([5.4, 3.9, 1.3, 0.4]),
#               array([5.1, 3.5, 1.4, 0.3]),
#               array([5.7, 3.8, 1.7, 0.3]),
#               array([5.1, 3.8, 1.5, 0.3]),
#               array([5.4, 3.4, 1.7, 0.2]),
#               array([5.1, 3.7, 1.5, 0.4]),
#               array([5.1, 3.3, 1.7, 0.5]),
#               array([5. , 3.4, 1.6, 0.4]),
#               array([5.2, 3.5, 1.5, 0.2]),
#               array([5.2, 3.4, 1.4, 0.2]),
#               array([5.4, 3.4, 1.5, 0.4]),
#               array([5.2, 4.1, 1.5, 0.1]),
#               array([5.5, 4.2, 1.4, 0.2]),
#               array([5.5, 3.5, 1.3, 0.2]),
#               array([4.9, 3.6, 1.4, 0.1]),
#               array([5.1, 3.4, 1.5, 0.2]),
#               array([5. , 3.5, 1.3, 0.3]),
#               array([5. , 3.5, 1.6, 0.6]),
#               array([5.1, 3.8, 1.9, 0.4]),
#               array([5.1, 3.8, 1.6, 0.2]),
#               array([5.3, 3.7, 1.5, 0.2])],
#              (4.704545454545454,
#               3.122727272727273,
#               1.4136363636363638,
#               0.2000000000000001): [array([4.9, 3. , 1.4, 0.2]),
#               array([4.7, 3.2, 1.3, 0.2]),
#               array([4.6, 3.1, 1.5, 0.2]),
#               array([4.6, 3.4, 1.4, 0.3]),
#               array([4.4, 2.9, 1.4, 0.2]),
#               array([4.9, 3.1, 1.5, 0.1]),
#               array([4.8, 3.4, 1.6, 0.2]),
#               array([4.8, 3. , 1.4, 0.1]),
#               array([4.3, 3. , 1.1, 0.1]),
#               array([4.6, 3.6, 1. , 0.2]),
#               array([4.8, 3.4, 1.9, 0.2]),
#               array([5. , 3. , 1.6, 0.2]),
#               array([4.7, 3.2, 1.6, 0.2]),
#               array([4.8, 3.1, 1.6, 0.2]),
#               array([4.9, 3.1, 1.5, 0.2]),
#               array([5. , 3.2, 1.2, 0.2]),
#               array([4.4, 3. , 1.3, 0.2]),
#               array([4.5, 2.3, 1.3, 0.3]),
#               array([4.4, 3.2, 1.3, 0.2]),
#               array([4.8, 3. , 1.4, 0.3]),
#               array([4.6, 3.2, 1.4, 0.2]),
#               array([5. , 3.3, 1.4, 0.2])],
#              (6.23658536585366,
#               2.8585365853658535,
#               4.807317073170731,
#               1.6219512195121943): [array([7. , 3.2, 4.7, 1.4]),
#               array([6.4, 3.2, 4.5, 1.5]),
#               array([6.9, 3.1, 4.9, 1.5]),
#               array([6.5, 2.8, 4.6, 1.5]),
#               array([6.3, 3.3, 4.7, 1.6]),
#               array([6.6, 2.9, 4.6, 1.3]),
#               array([6.1, 2.9, 4.7, 1.4]),
#               array([6.7, 3.1, 4.4, 1.4]),
#               array([5.6, 3. , 4.5, 1.5]),
#               array([6.2, 2.2, 4.5, 1.5]),
#               array([5.9, 3.2, 4.8, 1.8]),
#               array([6.3, 2.5, 4.9, 1.5]),
#               array([6.1, 2.8, 4.7, 1.2]),
#               array([6.4, 2.9, 4.3, 1.3]),
#               array([6.6, 3. , 4.4, 1.4]),
#               array([6.8, 2.8, 4.8, 1.4]),
#               array([6.7, 3. , 5. , 1.7]),
#               array([6. , 2.9, 4.5, 1.5]),
#               array([6. , 2.7, 5.1, 1.6]),
#               array([6. , 3.4, 4.5, 1.6]),
#               array([6.7, 3.1, 4.7, 1.5]),
#               array([6.3, 2.3, 4.4, 1.3]),
#               array([6.1, 3. , 4.6, 1.4]),
#               array([6.2, 2.9, 4.3, 1.3]),
#               array([5.8, 2.7, 5.1, 1.9]),
#               array([6.5, 3.2, 5.1, 2. ]),
#               array([6.4, 2.7, 5.3, 1.9]),
#               array([5.7, 2.5, 5. , 2. ]),
#               array([5.8, 2.8, 5.1, 2.4]),
#               array([6. , 2.2, 5. , 1.5]),
#               array([5.6, 2.8, 4.9, 2. ]),
#               array([6.3, 2.7, 4.9, 1.8]),
#               array([6.2, 2.8, 4.8, 1.8]),
#               array([6.1, 3. , 4.9, 1.8]),
#               array([6.3, 2.8, 5.1, 1.5]),
#               array([6.1, 2.6, 5.6, 1.4]),
#               array([6. , 3. , 4.8, 1.8]),
#               array([5.8, 2.7, 5.1, 1.9]),
#               array([6.3, 2.5, 5. , 1.9]),
#               array([6.5, 3. , 5.2, 2. ]),
#               array([5.9, 3. , 5.1, 1.8])],
#              (5.529629629629629,
#               2.6222222222222222,
#               3.940740740740741,
#               1.2185185185185188): [array([5.5, 2.3, 4. , 1.3]),
#               array([5.7, 2.8, 4.5, 1.3]),
#               array([4.9, 2.4, 3.3, 1. ]),
#               array([5.2, 2.7, 3.9, 1.4]),
#               array([5. , 2. , 3.5, 1. ]),
#               array([5.9, 3. , 4.2, 1.5]),
#               array([6. , 2.2, 4. , 1. ]),
#               array([5.6, 2.9, 3.6, 1.3]),
#               array([5.8, 2.7, 4.1, 1. ]),
#               array([5.6, 2.5, 3.9, 1.1]),
#               array([6.1, 2.8, 4. , 1.3]),
#               array([5.7, 2.6, 3.5, 1. ]),
#               array([5.5, 2.4, 3.8, 1.1]),
#               array([5.5, 2.4, 3.7, 1. ]),
#               array([5.8, 2.7, 3.9, 1.2]),
#               array([5.4, 3. , 4.5, 1.5]),
#               array([5.6, 3. , 4.1, 1.3]),
#               array([5.5, 2.5, 4. , 1.3]),
#               array([5.5, 2.6, 4.4, 1.2]),
#               array([5.8, 2.6, 4. , 1.2]),
#               array([5. , 2.3, 3.3, 1. ]),
#               array([5.6, 2.7, 4.2, 1.3]),
#               array([5.7, 3. , 4.2, 1.2]),
#               array([5.7, 2.9, 4.2, 1.3]),
#               array([5.1, 2.5, 3. , 1.1]),
#               array([5.7, 2.8, 4.1, 1.3]),
#               array([4.9, 2.5, 4.5, 1.7])],
#              (6.9125000000000005,
#               3.099999999999999,
#               5.846874999999999,
#               2.1312499999999996): [array([6.3, 3.3, 6. , 2.5]),
#               array([7.1, 3. , 5.9, 2.1]),
#               array([6.3, 2.9, 5.6, 1.8]),
#               array([6.5, 3. , 5.8, 2.2]),
#               array([7.6, 3. , 6.6, 2.1]),
#               array([7.3, 2.9, 6.3, 1.8]),
#               array([6.7, 2.5, 5.8, 1.8]),
#               array([7.2, 3.6, 6.1, 2.5]),
#               array([6.8, 3. , 5.5, 2.1]),
#               array([6.4, 3.2, 5.3, 2.3]),
#               array([6.5, 3. , 5.5, 1.8]),
#               array([7.7, 3.8, 6.7, 2.2]),
#               array([7.7, 2.6, 6.9, 2.3]),
#               array([6.9, 3.2, 5.7, 2.3]),
#               array([7.7, 2.8, 6.7, 2. ]),
#               array([6.7, 3.3, 5.7, 2.1]),
#               array([7.2, 3.2, 6. , 1.8]),
#               array([6.4, 2.8, 5.6, 2.1]),
#               array([7.2, 3. , 5.8, 1.6]),
#               array([7.4, 2.8, 6.1, 1.9]),
#               array([7.9, 3.8, 6.4, 2. ]),
#               array([6.4, 2.8, 5.6, 2.2]),
#               array([7.7, 3. , 6.1, 2.3]),
#               array([6.3, 3.4, 5.6, 2.4]),
#               array([6.4, 3.1, 5.5, 1.8]),
#               array([6.9, 3.1, 5.4, 2.1]),
#               array([6.7, 3.1, 5.6, 2.4]),
#               array([6.9, 3.1, 5.1, 2.3]),
#               array([6.8, 3.2, 5.9, 2.3]),
#               array([6.7, 3.3, 5.7, 2.5]),
#               array([6.7, 3. , 5.2, 2.3]),
#               array([6.2, 3.4, 5.4, 2.3])]})    
