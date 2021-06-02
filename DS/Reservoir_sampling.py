"""Reservoir sampling is a family of randomized algorithms for choosing a simple random sample, without replacement, 
of k items from a population of unknown size n in a single pass over the items. 
The size of the population n is not known to the algorithm and is typically too large for all n items to fit into main memory.

https://en.wikipedia.org/wiki/Reservoir_sampling#:~:text=Reservoir%20sampling%20is%20a%20family,to%20fit%20into%20main%20memory.

(* S has items to sample, R will contain the result *)
ReservoirSample(S[1..n], R[1..k])
  // fill the reservoir array
  for i := 1 to k
      R[i] := S[i]

  // replace elements with gradually decreasing probability
  for i := k+1 to n
    (* randomInteger(a, b) generates a uniform integer from the inclusive range {a, ..., b} *)
    j := randomInteger(1, i)   // important: inclusive range
    if j <= k
        R[j] := S[i]

"""
import random
def reservoir_sample(iterable, k):
    reservoir = []
    for i, item in enumerate(iterable):
        if i < k:
            reservoir.append(item)
        else:
            j = random.randint(0, i)
            if j < k:
                reservoir[j] = item
    return reservoir
  
  
reservoir_sample(range(1000000), 10)
# [973801, 423005, 360287, 932539, 372598, 139061, 147183, 44640, 686224, 165398]  
