# Advent of Code Solutions

[Advent of Code](https://adventofcode.com/) is an annual coding challenge inspired by the 25 days of Christmas. It consists of 25 coding challenges (one per day commencing 1st December) and uses serialised storytelling to deliver the problems. Please visit the linked website, where the challenge is hosted, for further information.

I am Chris and currently work as a data scientist. My interests include solving problems, especially mathematical problems. The purpose of this repo is to store my solutions during the coding challenge. My language of choice is clojure and I intend to backfill all previous solutions.

## Installation

The solutions are written in Clojure and require installation of the following
* Clojure https://clojure.org/guides/install_clojure
* Leiningen https://leiningen.org/#install

## Usage

The project structure is generated using Leiningen and I have adapted the app template to store scripts for each day's solutions (since on the most part they are indepdent). You will find the script for day n YYYY t at `src/aoc_[YYYY]/day[n].clj` and the corresponding namespace is `aoc-[YYYY].day[n]`.


## Examples

The run the code for solution of day 1 2023 run the following in the command line

`lein run -m aoc-2023.day1`

There are also unit tests each of these scripts, so for the same day's solution use the following command lne

`lein test aoc-2023.day1`

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2023 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
