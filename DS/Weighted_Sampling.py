"""
Weighted Sampling

You are given n numbers as well as probabilities p_0, p_1, ... , p_{n - 1}, which sum up to 1, 
how would you generate one of the n numbers according to the specified probabilities?

For example, if the numbers are 3, 5, 7, 11, and the probabilities are 9/18, 6/18, 2/18, 1/18, then 100000 calls to your program, 
3 should appear roughly 500000 times, 5 should appear roughly 333333 times, 7 should appear roughly 111111 times, and 11 should appear roughly 55555 times.
"""

import itertools, bisect, random
  
def weighted_sample(values, probs):
    cumulative_probs = [0.0] + list(itertools.accumulate(probs))          
    interval_idx = bisect.bisect(cumulative_probs, random.random()) - 1    # -1 because added[0.0], find place to insert random [0, 1]
    return values[interval_idx]
  
  
values = [3, 5, 7, 11]
probs = [9/18, 6/18, 2/18, 1/18]
result = {}
for _ in range(1000000):
    value = weighted_sample(values, probs)
    result[value] = result.get(value, 0) + 1
print(result)
 
# {3: 500175, 5: 333546, 7: 110936, 11: 55343}  

==================================================================
# 2. numpy
from numpy.random import choice
#draw = choice(list_of_candidates, number_of_items_to_pick, p=probability_distribution)
values = [3, 5, 7, 11]
probs = [9/18, 6/18, 2/18, 1/18]
draw = choice(values, 1000000, p=probs)
result = {}
for i in range(1000000):
    result[draw[i]] = result.get(draw[i], 0) + 1
print(result)

==================================================================
3. random.choice
import random
draw = random.choices(population=values, weights=probs, k=1000000)
result = {}
for i in range(1000000):
    result[draw[i]] = result.get(draw[i], 0) + 1
print(result)
