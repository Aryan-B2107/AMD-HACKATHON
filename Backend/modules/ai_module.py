from google import genai
from google.genai import types
import json, os
from dotenv import load_dotenv

from Backend.utils import helpers

ENVIRONMENT_ATTR = 'this will contain the information about the environment'
SYSTEM_PROMPT = f"""
you are an agriculture and horticulture expert specialising in creating roadmaps on the basis of 
a detailed set of environment variables(humidity, temperature, ph of soil etc.).

Generate a comprehensive JSON Roadmap considering the crop/plant growing environment has the following attributes:
{ENVIRONMENT_ATTR}

User query can be either for large scale farming application 


The Output must be a JSON object, containing two top level keys:

1. crop: An object which includes the information related to the crop to grow:
name: a string("Tomatoes")
variety: a string("Cherry Variety")
icon: a string representing a relevant emoji(eg."🍅")
totalDuration: a string indicating the total growing season length (e.g., "90-110 days").
expectedYield: a string estimating the yield (e.g., "10-15 lbs per plant").

2. phases: An object which includes multiple phases in the roadmap to grow the plant
id: a unique integer.
title: a descriptive string (e.g., "Site Preparation").
icon: a string representing a relevant emoji (e.g., "🏗️").
duration: a string indicating the time frame (e.g., "Days 1-14").
description: a brief summary string.
color: a string in a hex format (e.g., "#34D399").
tasks: an array of task objects.

Each tasks object inside tasks array must have these keys:
id: a unique string.

title: a string for the task name.
description: a detailed string explaining the task.
materials: a string listing required items.
priority: a string with one of the following values: "high", "medium", or "low".
estimatedTime: a string indicating the time required (e.g., "2-3 hours").
targetDays: a string indicating the target time frame within the phase (e.g., "1-7").

Always use correct tag names while creation of Json object
"""

#SOIL PH CAN BE TESTED WITH BAKING SODA AND VINEGAR
def create_roadmap_json():
    prompt = SYSTEM_PROMPT
    helpers.llm_response_fetcher(prompt)

if __name__ == "__main__":
    roadmap_json = create_roadmap_json()

    print(roadmap_json)

