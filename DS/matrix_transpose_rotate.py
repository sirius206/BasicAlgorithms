# transpose matrix
def transpose(matrix):
    n = len(matrix)
    if n <= 1:
        return matrix
    for i in range(n):
        for j in range(i + 1, n):
            temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
    return matrix
  
  
"""
Rotate matrix
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise and anticlockwise).
You have to rotate the image in-place.
"""
def rotate_clock(matrix):
    matrix = transpose(matrix)
    print(matrix)
    n = len(matrix)
    for i in range(n):
        for j in range(n // 2):
            matrix[i][j], matrix[i][n - j -1] =  matrix[i][n - j -1], matrix[i][j]
    return matrix
  
def rotate_counterclock(matrix):
    matrix = transpose(matrix)
    print(matrix)
    n = len(matrix)
    for i in range(n):
        for j in range(n // 2):
            matrix[j][i], matrix[n - j -1][i] =  matrix[n - j -1][i], matrix[j][i]
    return matrix  

if __name__ == "__main__":
    assert transpose([[1, 2, 3],
                         [4, 5, 6],
                         [7, 8, 9]]) == [[1, 4, 7], 
                                         [2, 5, 8], 
                                         [3, 6, 9]]  
    assert rotate_clock([[1, 2, 3],
                         [4, 5, 6],
                         [7, 8, 9]]) == [[7, 4, 1], 
                                         [8, 5, 2], 
                                         [9, 6, 3]]
    
    assert rotate_counterclock([[1, 2, 3],
                             [4, 5, 6],
                             [7, 8, 9]]) == [[3, 6, 9], 
                                             [2, 5, 8], 
                                             [1, 4, 7]]
