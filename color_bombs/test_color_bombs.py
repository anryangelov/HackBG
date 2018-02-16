import color_bombs
import pytest


cases = [
('''
GGGB
GRGG
RGRB
RRRB
''',
4),
('''
RRRB
GRGB
RGRB
RRRB
''',
3),
('''
YGGB
GRGG
RGRB
RRRB
''',
5),
('''
YGGB
GRGG
RGRB
RRwB
''',
6),
('''
YGGBYG
GRGGGG
RGRBYG
RRRBYG
''',
7),
('''
G
''',
1),
('''
GGGGBBBB
''',
2),
('''
YYYYBYYYY
BBYYBYBYY
BYBYBBBBY
BBYBYYYBY
YYBYYBYBY
YYYYYYBBY
YYYYYYYYY
''',
2)
]


@pytest.mark.parametrize('input_data, expected_result', cases)
def test_connected_bombs(input_data, expected_result):
    got_result = color_bombs.connected_bombs(input_data)
    assert expected_result == got_result
