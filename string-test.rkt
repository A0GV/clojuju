#lang racket

(require racket/string)

;; Test string-contains?
(define test-str "system: cup")
(define search-str "system:")

(printf "Testing string-contains?~n")
(printf "String: ~a~n" test-str)
(printf "Search: ~a~n" search-str)

;; Try different approaches
(define result1 (string-contains? test-str search-str))
(printf "string-contains? result: ~a~n" result1)

;; Try regexp-match
(define result2 (regexp-match (regexp-quote search-str) test-str))
(printf "regexp-match result: ~a~n" result2)

;; Try string position with regexp-match-positions
(define result3 (regexp-match-positions (regexp-quote search-str) test-str))
(printf "regexp-match-positions result: ~a~n" result3)