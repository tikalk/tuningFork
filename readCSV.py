#! /usr/bin/env python
# Load CSV (using python)
# Load CSV
import numpy as np
filename = 'tuningFork.csv'
# raw_data = open(filename, 'rb')
data = np.genfromtxt('tuningFork.csv',skip_header=1,delimiter=',')
# data = numpy.genfromtxt(raw_data, delimiter=";")
print(data[0,11])
