def connected_bombs(input_data):
    matrix = [list(row) for row in input_data.split()]
    row_boundary = len(matrix)
    column_boundary = len(matrix[0])

    connected = 0
    for row, row_list in enumerate(matrix):
        for column, color in enumerate(row_list):
            if color != 0:
                mark_neighbours(matrix, color, row, column, row_boundary, column_boundary)
                connected += 1
    return connected


def mark_neighbours(matrix, color, row, column, row_boundary, column_boundary):
    if color == 0:  # already traversed
        return
    new_color = matrix[row][column]
    if color != new_color:
        return
    matrix[row][column] = 0  # mark position is traversed
    for r, c in get_neighbours_position(row, column, row_boundary, column_boundary):
        mark_neighbours(matrix, new_color, r, c, row_boundary, column_boundary)


def get_neighbours_position(row, column, row_boundary, column_boundary):
    for r in (row - 1, row, row + 1):
        for c in (column - 1, column, column + 1):
            if 0 <= r < row_boundary and 0 <= c < column_boundary:
                if r == row and c == column:
                    continue
                yield r, c
