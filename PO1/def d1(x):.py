import numpy as np

# Define the coefficients matrix
A = np.array([[5, 0, 10], [1, 1, -1], [-8, 0, -16]])

# Define the constant matrix
b = np.array([0, 0, 0])

# Use numpy's least squares solver to solve for c1, c2, c3
c, residuals, rank, s = np.linalg.lstsq(A, b, rcond=None)

# Check if the solution is non-trivial (i.e., not all zeros)
if np.all(c == 0):
    print("The vectors are linearly dependent.")
else:
    print("The vectors are linearly independent.")

# The dimension of W is the rank of the matrix A
print("The dimension of W is", rank)