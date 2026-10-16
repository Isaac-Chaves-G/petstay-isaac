import unittest
from select_case import case_for_issue, normalize_case


class CaseSelectionTest(unittest.TestCase):
    def test_formats_used_in_failed_runs(self):
        for value, expected in [('Tc01', 'TC01'), ('@TC12', 'TC12'), ('@TC14', 'TC14'),
                                (' @tc01 ', 'TC01'), ('tc1', 'TC01')]:
            with self.subTest(value=value):
                self.assertEqual(expected, normalize_case(value))

    def test_every_case(self):
        for number in range(1, 30):
            expected = f'TC{number:02d}'
            for value in (expected, expected.lower(), '@' + expected, '@' + expected.lower()):
                self.assertEqual(expected, normalize_case(value))

    def test_full_suite_aliases(self):
        for value in (None, '', '  ', 'todos', 'TODOS', '@HU02'):
            self.assertEqual('todos', normalize_case(value))

    def test_invalid_inputs_do_not_fall_back_to_full_suite(self):
        for value in ('@', 'TC00', 'TC30', '@@TC01', 'TC01,TC02', '--tags @TC01', '$(echo x)'):
            with self.subTest(value=value), self.assertRaises(ValueError):
                normalize_case(value)

    def test_issue_selection_uses_real_mapping_not_title(self):
        mapping = {'cases': {'TC12': {'number': 78, 'title': 'Título cambiado'}}}
        self.assertEqual('TC12', case_for_issue(78, mapping))
        with self.assertRaises(ValueError):
            case_for_issue(12, mapping)


if __name__ == '__main__':
    unittest.main()
